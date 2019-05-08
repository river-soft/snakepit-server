import Vue from 'vue'
import App from './App.vue'
import router from "./components/router"
import ui from "element-ui"
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false;
Vue.use(ui)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

