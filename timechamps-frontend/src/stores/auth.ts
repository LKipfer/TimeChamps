import { defineStore } from "pinia";
import TokenStorageService from "@/services/token-storage.service";
import type User from "@/types/user";
import AuthService from "@/services/auth.service";
import UserService from "@/services/user.service";
import type { AxiosResponse } from "axios";
import { Role } from "@/types/role";

interface AuthState {
  loggedIn: boolean;
  role: Role;
}

const userService = new UserService();
const accessToken = TokenStorageService.getToken();

export const useAuthStore = defineStore("auth", {
  state: (): AuthState =>
    accessToken
      ? { loggedIn: true, role: Role.ADMIN }
      : { loggedIn: false, role: Role.USER },
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
    register(user: User): Promise<AxiosResponse<any, any>> {
      return userService.register(user).then(
        (res: AxiosResponse<any>) => {
          this.loggedIn = false;
          return Promise.resolve(res);
        },
        (error) => {
          this.loggedIn = false;
          return Promise.reject(error);
        }
      );
    },
  },
});
