import { defineStore } from "pinia";
import TokenStorageService from "@/services/token-storage.service";
import type User from "@/types/user";
import AuthService from "@/services/auth.service";
import type { AxiosResponse } from "axios";

interface AuthState {
  loggedIn: boolean;
}

const accessToken = TokenStorageService.getToken();

export const useAuthStore = defineStore("auth", {
  state: (): AuthState =>
    accessToken ? { loggedIn: true } : { loggedIn: false },
  getters: {},
  actions: {
    login(user: User): Promise<AxiosResponse<any, any>> {
      return AuthService.login(user).then(
        (user: AxiosResponse<any>) => {
          this.loggedIn = true;
          return Promise.resolve(user);
        },
        (error) => {
          this.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
    logout(): void {
      AuthService.logout();
      this.loggedIn = false;
    },
    register(user: User): Promise<AxiosResponse<any, any>> {
      return AuthService.register(user).then(
        (response: AxiosResponse<any>) => {
          this.loggedIn = false;
          return Promise.resolve(response.data);
        },
        (error) => {
          this.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
  },
});
