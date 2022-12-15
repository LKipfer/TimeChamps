import AuthService from '../services/auth.service';
import { User } from '../model/user';
import { ActionContext } from 'vuex';
import TokenStorageService from '../services/token-storage.service';
import { State } from './index';

const accessToken = TokenStorageService.getToken();
export interface AuthState {
  status: {
    loggedIn: boolean;
  };
}

const initialState: AuthState = accessToken ? { status: { loggedIn: true } } : { status: { loggedIn: false } };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }: ActionContext<string, State>, user: User) {
      return AuthService.login(user).then(
        user => {
          commit('loginSuccess', user);
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }: ActionContext<string, State>) {
      AuthService.logout();
      commit('logout');
    },
    register({ commit }: ActionContext<string, State>, user: User) {
      return AuthService.register(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    loginSuccess(state: AuthState) {
      state.status.loggedIn = true;
      // state.user = user;
    },
    loginFailure(state: AuthState) {
      state.status.loggedIn = false;
      // state.user = null;
    },
    logout(state: AuthState) {
      state.status.loggedIn = false;
      // state.user = null;
    },
    registerSuccess(state: AuthState) {
      state.status.loggedIn = false;
    },
    registerFailure(state: AuthState) {
      state.status.loggedIn = false;
    },
  },
};
