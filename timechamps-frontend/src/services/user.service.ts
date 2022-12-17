import axios from "axios";
import type User from "@/types/user";
import type { AxiosResponse } from "axios";

const API_URL = "/api/user/";

export default class UserService {
  register(user: User): Promise<AxiosResponse<any, any>> {
    return (
      axios
        .post(API_URL + "register", {
          email: user.email,
          password: user.password,
        })
        // ToDo: Define return type
        .then((res: AxiosResponse<any>) => res.data)
    );
  }
}
