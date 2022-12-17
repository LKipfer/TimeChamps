import { defineStore } from "pinia";
import TokenStorageService from "@/services/token-storage.service";
import type User from "@/types/user";
import AuthService from "@/services/auth.service";
import type { AxiosResponse } from "axios";

interface AuthState {
  status: {
    loggedIn: boolean;
  };
}

const accessToken = TokenStorageService.getToken();

export const useAuthStore = defineStore("auth", {
  state: (): AuthState =>
    accessToken
      ? { status: { loggedIn: true } }
      : { status: { loggedIn: false } },
  getters: {},
  actions: {
    login(user: User) {
      return AuthService.login(user).then(
        (user: AxiosResponse<any>) => {
          this.status.loggedIn = true;
          return Promise.resolve(user);
        },
        (error) => {
          this.status.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
    logout() {
      AuthService.logout();
      this.status.loggedIn = false;
    },
    register(user: User) {
      return AuthService.register(user).then(
        (response: AxiosResponse<any>) => {
          this.status.loggedIn = false;
          return Promise.resolve(response.data);
        },
        (error) => {
          this.status.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
  },
});
