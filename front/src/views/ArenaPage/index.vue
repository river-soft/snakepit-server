<template>
    <div>
        <div class="timeContainer">
            <p>Время : {{time}}</p>
            <p>Ваше имя : {{username}}</p>
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
            }
        },
        created() {
            this.intervalHandle = setInterval(() => {
                gameMap().then((response) => {
                    this.time = moment.unix(response.data.time).format('mm:ss');
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

    .timeContainer{
        width:100%;
        height:100px;
        color:#4682B4;
        font-size: 3em;
        position: relative;
        display:block;
       font-family:Roboto;
    }
</style>
