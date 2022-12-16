import { createRouter, createWebHistory } from 'vue-router';
import DailyTimesheet from '../views/DailyTimesheet.vue';
import SignIn from '../views/SignIn.vue';
import TimeAdmin from '../views/TimeAdmin.vue';
import store from '../store';

const routes = [
  { path: '/', name: 'DailyTimesheet', component: DailyTimesheet },
  { path: '/admin', name: 'Admin', component: TimeAdmin },
  { path: '/login', name: 'SignIn', component: SignIn },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach(async to => {
  const isAuthenticated = store.state.auth.status.loggedIn;
  if (!isAuthenticated && to.name !== 'SignIn') {
    // redirect the user to the login page
    return { name: 'SignIn' };
  }
});

export default router;
