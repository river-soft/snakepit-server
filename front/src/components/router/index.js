import Vue from 'vue'
import Vuerouter  from 'vue-router';
import Registration from "../Registration";
import GameMapView from "../GameMapView";


Vue.use(Vuerouter);

export default new Vuerouter({
    routes:[
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