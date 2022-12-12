import {createRouter, createWebHistory} from "vue-router";
import Admin from "../views/Admin.vue";
import DailyTimesheet from "../views/DailyTimesheet.vue";
import SignIn from "../views/SignIn.vue"

const routes = [
    { path: '/', component: DailyTimesheet },
    { path: '/admin', component: Admin },
    { path: '/login', component: SignIn}
];

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;
