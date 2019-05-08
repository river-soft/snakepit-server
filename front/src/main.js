import Vue from 'vue'
import App from './App.vue'
import Registration from "./components/Registration";
import router from "./components/router"
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

new Vue({
  router,
  render: h => h(Registration),
}).$mount('#form-reg');
