import Vue from 'vue'
import App from './App.vue'
import router from "./components/router/index.js"
import ui from "element-ui"
import 'element-ui/lib/theme-chalk/index.css';
import {AxiosProxyConfig as auth} from "axios";


router.beforeEach((to, from, next) => {
  if(to.meta.requiresAuth){
    next({
      path:'/'
    })
  }else if(store.state.auth){
    next()
  }
});
Vue.use(ui);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');



