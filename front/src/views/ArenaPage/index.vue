<template>
    <div class="main-wrapper">
        <div class="data-container">
            <ul>
                <li><p> <i class="material-icons av_timer">av_timer</i>{{time}}</p></li>
                <li><p> <i class="material-icons people">people</i>{{username}}</p></li>
                <li><p> <i class="material-icons people attach_money">attach_money</i>{{rating}}</p></li>
            </ul>
            <div class="button-panel">
                <router-link to="/statistic"><button>Статистика</button></router-link>
                <router-link to="/HistoryMatch"><button>История матчей</button></router-link>
            </div>
        </div>
        <br>
        <div class="content-arena">
            <arena :cells="mapData" :users="usersData"/>
        </div>
    </div>
</template>
<script>
    import Arena from '../../components/Arena/index';
    import { gameMap } from "../../api/gameRepository";
    let moment  = require('moment');

    export default {
        name: 'ArenaPage',
        components: { Arena },
        data() {
            return {
                mapData: [],
                usersData: [],
                intervalHandle: null,
                time:'Ожидание игроков',
                username:'',
                rating:'',
            }
        },
        created() {
            this.intervalHandle = setInterval(() => {
                gameMap().then((response) => {
                    this.time = moment.unix(response.data.time).format('mm:ss');
                    this.rating = response.data.currentUser.rating;
                    this.mapData = response.data.map;
                    this.usersData = response.data.users;
                    this.usersData.unshift(response.data.currentUser);
                    this.username = response.data.currentUser.name;
                    console.log("Response new data");
                });
            }, 1000);
        },
        destroyed() {
            clearInterval(this.intervalHandle)
        }
    }
</script>
<style>
    @import url('https://fonts.googleapis.com/icon?family=Material+Icons');

    *{
        margin:0;
        padding: 0;
    }
    .main-wrapper{
        width:100%;
        display:flex;
        flex-wrap: wrap;
        height:auto;
    }
    .data-container{
        flex-direction: column;
        float:left;
        width:15%;
        color:#4682B4;
        position: sticky;
        top:0;
        bottom:0;
        display:block;
        height:100vh;
        background-color: #c7d6e2;
    }
    ul{
        list-style: none;
    }
    .data-container li{
        border-bottom: 1px solid #252525;
        width:100%;
        background-color:transparent;
        padding-bottom: 4%;
        padding-top: 2%;
        margin:10% auto;
        font-size: 1.3em;
        height:auto;
        color:white;
    }
    .data-container li:first-child{
        margin-top:70%;
    }
    .data-container li p{
        color:#252525;
        font-size: 1.1em;
        padding-right: 20%;
        text-align: center;
    }
    .content-arena{
        width:80%;
        flex-direction: column;
        float:left;
        height:auto;
    }
    .material-icons{
        float:left;
        padding-left:10%;
        font-size: 30px;
    }
    .button-panel{
        width:100%;
        display:flex;
        flex-wrap: wrap;
        margin:5% auto;
    }
    .button-panel button{
        border:0;
        outline:0;
        background-color:#409eff;
        width:75%;
        height:60px;
        color:white;
        display:block;
        font-size: 1.2em;
        margin:2% auto ;
    }
    a {
        width:100%;
        text-decoration: none;
        cursor:pointer;
    }
</style>
