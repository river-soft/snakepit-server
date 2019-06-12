import Vue from 'vue'
import App from './App.vue'
import router from "./components/router"
import ui from "element-ui"
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios'
import swal from 'sweetalert'

//do request
Axios.interceptors.request.use((config) => {
      console.log('My request is ' + config);

        return config
     },(error) =>{
        router.push('/');
        swal({
          title:'Ошибка',
          text:'Пожалуйста , авторизируйтесь',
          button:'Ok'
        });
        console.log(error);
        return Promise.reject(error)
    });
//response
Axios.interceptors.response.use((response) => {
  console.log('My response is ' + response);

  return response
},(error) =>{
  router.push('/');
  swal({
    title:'Ошибка',
    text:'Пожалуйста , авторизируйтесь',
    button:'Ok'
  });
  return Promise.reject(error)
});

Vue.use(ui);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');




router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    let checkAuthUser = JSON.parse(window.localStorage.getItem('currentUser'));
    if ( !checkAuthUser ) {
      next({ path: '/login' })
    } else if ( checkAuthUser ) {
      next()
    }
  } else {
    next()
  }
});