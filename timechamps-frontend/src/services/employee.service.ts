import type Employee from "@/types/employee";

export default class EmployeeService {
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
