<template>
    <div>
        <div class="timeContainer">
            <p>Время : {{time}}</p>
        </div>
        <arena :cells="mapData"/>
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
                time:1,
            }
        },
        created() {
            this.intervalHandle = setInterval(() => {
                gameMap().then((response) => {
                    this.time = moment.unix(response.data.time).format('mm:ss');
                    this.mapData = response.data.map;
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

    /*.timeContainer{*/
    /*    width:100%;*/
    /*    height:100px;*/
    /*    color:white;*/
    /*    font-size: 4em;*/
    /*    position: relative;*/
    /*    display:block;*/
    /*}*/
</style>
