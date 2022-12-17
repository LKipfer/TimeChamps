import axios from "axios";
import type { AxiosResponse } from "axios";
import authHeader from "@/services/auth.header";
import type Employee from "@/types/employee";

const API_URL = "/api/employee/";

export default class EmployeeService {
  getAll(): Promise<Employee[]> {
    return axios
      .get(API_URL + "all", {
        headers: authHeader(),
      })
      .then((res: AxiosResponse<Employee[]>) => res.data);
  }
}
