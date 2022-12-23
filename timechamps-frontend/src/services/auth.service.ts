/*
 * @author Isabelle Bustamante

 */

import axios from "axios";
import TokenStorageService from "./token-storage.service";
import type User from "@/types/user";
import type { AxiosResponse } from "axios";

const API_URL = "/api/login/";

class AuthService {
  login(user: User): Promise<string> {
    return axios
      .post(API_URL + "authenticate", {
        username: user.username,
        password: user.password,
      })
      .then((res: AxiosResponse<string>) => {
        if (res.data) {
          TokenStorageService.storeToken(res.data);
        }

        return res.data;
      });
  }

  logout(): void {
    TokenStorageService.clearToken();
  }
}

export default new AuthService();
