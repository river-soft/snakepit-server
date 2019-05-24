<template>
    <div>
        <div class="data-container">
            <ul>
                <li><p>Время : {{time}}</p></li>
                <li><p>Ваше имя : {{username}}</p></li>
                <li><p>Ваш рейтинг: {{rating}}</p></li>
            </ul>
        </div>
        <br>
        <arena :cells="mapData" :users="usersData"/>
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
    *{
        margin:0;
        padding: 0;
    }

    .data-container{
        width:15%;
        color:#4682B4;
        position: absolute;
        display:block;
        left:1%;
        top:2%;
    }
    ul{
        list-style: none;
    }
    .data-container li{
        width:100%;
        background-color:transparent;
        border: 1px solid white;
        box-shadow: 2px 2px 2px rgba(0,0,0,0.3);
        padding-bottom: 4%;
        padding-top: 2%;
        margin:3% auto;
        font-size: 1.3em;
        height:auto;
        color:white;
    }
</style>
