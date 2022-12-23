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

  addEmployee(employee: Employee): Promise<Employee> {
    return axios
      .post(API_URL + "add", employee, {
        headers: authHeader(),
      })
      .then((res: AxiosResponse<Employee>) => res.data);
  }

  updateEmployee(employee: Employee): Promise<Employee> {
    return axios
      .put(API_URL + "update", employee, {
        headers: authHeader(),
      })
      .then((res: AxiosResponse<Employee>) => res.data);
  }

  deleteEmployee(id: number): Promise<void> {
    return axios
      .delete(API_URL + `delete/${id}`, {
        headers: authHeader(),
      })
      .then((res: AxiosResponse<any>) => res.data);
  }
}
