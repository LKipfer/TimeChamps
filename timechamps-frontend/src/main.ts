import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import PrimeVue from "primevue/config";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import Menubar from "primevue/menubar";

import "primevue/resources/themes/saga-blue/theme.css";
import "primevue/resources/primevue.min.css";
import "primeicons/primeicons.css";
import "/node_modules/primeflex/primeflex.css";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Toolbar from "primevue/toolbar";
import Dialog from "primevue/dialog";
import InputNumber from "primevue/inputnumber";
import ToastService from "primevue/toastservice";
import Toast from "primevue/toast";

const pinia = createPinia();
const app = createApp(App);

app.use(pinia);
app.use(router);
app.use(PrimeVue);
app.use(ToastService);
app.component("MenuBar", Menubar);
app.component("InputText", InputText);
app.component("PButton", Button);
app.component("DataTable", DataTable);
app.component("PColumn", Column);
app.component("ToolBar", Toolbar);
app.component("PDialog", Dialog);
app.component("InputNumber", InputNumber);
app.component("PToast", Toast);
app.mount("#app");
