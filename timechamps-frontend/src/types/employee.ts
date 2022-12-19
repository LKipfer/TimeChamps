export default interface Employee {
  id: number;
  surname: string;
  name: string;
  address: string;
  place: string;
  jobTitle?: string;
  email: string;
  targetTime: number;
  employeeCode: string;
}
