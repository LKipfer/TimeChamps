import { createStore } from 'vuex';
import { auth, AuthState } from './auth.module';

export interface State {
  auth: AuthState;
}

const store = createStore<State>({
  modules: {
    auth,
  },
});

export default store;
