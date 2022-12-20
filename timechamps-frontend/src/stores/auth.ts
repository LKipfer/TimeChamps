import { defineStore } from "pinia";
import TokenStorageService from "@/services/token-storage.service";
import type User from "@/types/user";
import AuthService from "@/services/auth.service";

interface AuthState {
  loggedIn: boolean;
}

const accessToken = TokenStorageService.getToken();

export const useAuthStore = defineStore("auth", {
  state: (): AuthState =>
    accessToken ? { loggedIn: true } : { loggedIn: false },
  getters: {},
  actions: {
    login(user: User): Promise<string> {
      return AuthService.login(user).then(
        (token: string) => {
          this.loggedIn = true;
          return Promise.resolve(token);
        },
        (error: any) => {
          this.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
    logout(): void {
      AuthService.logout();
      this.loggedIn = false;
    },
  },
});
