import axios from "axios";
import authHeader from "./auth.header";

const API_URL = "/api/timestamps/";
export default class TimestampService {
  getTimestamps() {
    return axios
      .get(API_URL + "all", {
        headers: authHeader(),
      })
      .then((response) => {
        return response.data;
      });
  }
}
