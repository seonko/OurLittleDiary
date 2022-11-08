import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axiosInstance from './plugins/axios'
import VueCookies from 'vue-cookies'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'

const app = createApp(App).use(store).use(router).use(VueCookies)
app.config.globalProperties.axios = axiosInstance

app.mount('#app')
