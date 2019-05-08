import Vue from 'vue'
import Vuerouter  from 'vue-router';

let reg = require('@/components/HelloWorld.vue')
Vue.use(Vuerouter);

export default new Vuerouter({
    routes:[
        {
            path:'/',
            name:'registration',
            component: reg,
        }
    ]
});