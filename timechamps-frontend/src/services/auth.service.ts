import axios from 'axios';
import { User } from '../model/user';
import TokenStorageService from './token-storage.service';

const API_URL = '/api/auth/';

class AuthService {
  login(user: User) {
    return axios
      .post(API_URL + 'authenticate', {
        email: user.email,
        password: user.password,
      })
      .then(response => {
        console.log(response);
        if (response.data) {
          TokenStorageService.storeToken(JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    TokenStorageService.clearToken();
  }

  register(user: User) {
    return axios.post(API_URL + 'signup', {
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthService();
