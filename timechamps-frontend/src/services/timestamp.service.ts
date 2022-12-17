import axios from "axios";
import type { AxiosResponse } from "axios";
import authHeader from "./auth.header";
import type TimeStamp from "@/types/timestamp";

const API_URL = "/api/timestamps/";
const config = { headers: authHeader() };
export default class TimestampService {
  getTimestamps(): Promise<TimeStamp[]> {
    return axios
      .get(API_URL + "all", config)
      .then((res: AxiosResponse<TimeStamp[]>) => res.data);
  }

  addTimestamp(): Promise<TimeStamp> {
    return axios
      .post(API_URL + "add", undefined, config)
      .then((res: AxiosResponse<TimeStamp>) => res.data);
  }
}
