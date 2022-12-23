/*
 * @author Isabelle Bustamante

 */

import axios from "axios";
import type User from "@/types/user";
import type { AxiosResponse } from "axios";
import type Employee from "@/types/employee";

const API_URL = "/api/user/";

export default class UserService {
  register(user: User): Promise<Employee> {
    return axios
      .post(API_URL + "register", {
        username: user.username,
        password: user.password,
      })
      .then((res: AxiosResponse<Employee>) => res.data);
  }
}
