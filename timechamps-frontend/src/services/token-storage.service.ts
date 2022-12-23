
//author Isabelle Bustamante
class TokenStorageService {
  storeToken(token: string | null): void {
    if (!token) {
      this.clearToken();
    } else {
      localStorage.setItem("access_token", token);
    }
  }

  getToken(): string | undefined {
    return localStorage.getItem("access_token") || undefined;
  }

  clearToken(): void {
    localStorage.removeItem("access_token");
  }
}

export default new TokenStorageService();
