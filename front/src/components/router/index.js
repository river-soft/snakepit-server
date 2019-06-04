import Vue from 'vue'
import Vuerouter  from 'vue-router';
import Registration from "../Registration";
import GameMapView from "../GameMapView";
import Homepage from "../Homepage";
import ArenaPage from '../../views/ArenaPage';
import Login from "../Login";
import statistic from "../statistic";
import HistoryMatch from "../HistoryMatch";
import GameRules from "../GameRules";



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
            path: '/arena',
            component: ArenaPage,
            meta: {
                requiresAuth:true
            }
        },
        {
            path: '/login',
            component: Login
        },
        {
            path:'/statistic',
            component:statistic,
            meta: {
                requiresAuth:true
            }
        },
        {
            path:'/HistoryMatch',
            component:HistoryMatch,
            meta: {
                requiresAuth:true
            }
        },
        {
            path:'/gameRules',
            component:GameRules,
            meta: {
                requiresAuth:true
            }
        }
    ],
});
