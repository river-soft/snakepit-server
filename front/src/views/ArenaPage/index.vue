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
        <arena :cells="mapData" :name="username"/>
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
                    this.rating = response.data.rating;
                    this.mapData = response.data.map;
                    this.username = response.data.username;
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
        height:auto;
        color:#4682B4;
        position: absolute;
        display:block;
        right:1%;
        top:2%;
    }
    ul{
        list-style: none;
    }
    .data-container li{
        width:100%;
        background-color: #222226;
        box-shadow: 2px 2px 2px rgba(0,0,0,0.3);
        padding-bottom: 2%;
        padding-top: 2%;
        margin:3% auto;
        font-size: 1.3em;
        height:auto;
    }
</style>
