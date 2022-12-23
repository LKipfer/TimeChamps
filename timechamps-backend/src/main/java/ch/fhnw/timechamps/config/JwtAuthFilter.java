package ch.fhnw.timechamps.config;

import ch.fhnw.timechamps.model.User;
import ch.fhnw.timechamps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=b9O9NI-RJ3o&t=1598s
 */

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String username;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7); //On character 7 because Bearer is 6 characters?
        username = jwtUtils.extractUsername(jwtToken);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> optionalUser = userService.findByUsername(username);
            if (optionalUser.isPresent()) {
                final User user = optionalUser.get();
                if (jwtUtils.isTokenValid(jwtToken, user)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
