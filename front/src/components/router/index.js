import Vue from 'vue'
import Vuerouter  from 'vue-router';
import Registration from "../Registration";
import GameMapView from "../GameMapView";
import Homepage from "../Homepage";


Vue.use(Vuerouter);

export default new Vuerouter({
    routes:[
        {
          path:'/',
          component:Homepage,
        },
        {
            path:'/registration',
            component: Registration,
        },
        {
            path:'/map',
            component:GameMapView,
        }
    ]
});