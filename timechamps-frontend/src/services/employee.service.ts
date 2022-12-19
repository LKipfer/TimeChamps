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

  getMe(): Promise<Employee> {
    // ToDo: Implement Backend API
    return new Promise((resolve) => {
      const employee: Employee = {
        email: "isabelle.bustamante@students.fhnw.ch",
        name: "Bustamante",
        surname: "Isabelle",
        targetTime: 8.4,
      } as Employee;
      resolve(employee);
    });
  }
}
