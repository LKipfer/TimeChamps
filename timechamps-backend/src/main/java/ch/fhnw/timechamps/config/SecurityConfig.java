package ch.fhnw.timechamps.config;


import ch.fhnw.timechamps.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Lukas Kipfer
 * @source: https://www.youtube.com/watch?v=b9O9NI-RJ3o&t=1598s
 */

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDao userDao;


    @Bean
    //Deleted "@Order" so that this Bean will be used instead of the default SecurityFilterChain
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/auth/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**")
                .permitAll() //we tell Spring, everytime you see the pattern /auth/, just admit all. I assume it's so it doesn't forbid the auth-request itself
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Stateless = we dont want to save the session state
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //This way we demand that the jwtAuthFilter is used before the other filter
                ;
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService()); //There are many different .set Methods. We use this one because we want Spring to use the method we created
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { //throw exception for the case when Authentication Manager is not present
        return config.getAuthenticationManager();
    }

    @Bean
    //TODO: Change encryption method before deploying
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //Should only be used for local development. This does NOT decrypt passwords! Also is deprecated.
        // return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService () {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userDao.findUserByEmail(email);
            }
        };
    }

}
