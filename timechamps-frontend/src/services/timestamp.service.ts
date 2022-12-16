import axios from 'axios';
import authHeader from "./auth.header";

const API_URL = '/api/timestamps/';
class TimestampService {
    getAll() {
        return axios
            .get(API_URL + 'all', {
                headers: authHeader(),
            })
            .then(response => {
                return response.data;
            });
    }
}

export default new TimestampService();
