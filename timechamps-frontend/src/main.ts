import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Router from './router'
import PrimeVue from 'primevue/config';
import InputText from "primevue/inputtext";
import Button from "primevue/button";

import 'primevue/resources/themes/saga-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import '/node_modules/primeflex/primeflex.css'

createApp(App)
    .use(Router)
    .use(PrimeVue)
    .component('InputText', InputText)
    .component('Button', Button)
    .mount('#app')
