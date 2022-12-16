import axios from 'axios';
import { User } from '../model/user';
import TokenStorageService from './token-storage.service';

const API_URL = '/api/login/';

class AuthService {
  login(user: User) {
    return axios
      .post(API_URL + 'authenticate', {
        email: user.email,
        password: user.password,
      })
      .then(response => {
        if (response.data) {
          TokenStorageService.storeToken(response.data);
        }

        return response.data;
      });
  }

  logout() {
    // TODO logout API
    TokenStorageService.clearToken();
  }

  register(user: User) {
    // TODO register API
    return axios.post(API_URL + 'signup', {
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthService();
