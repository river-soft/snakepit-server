<template>
    <div>
        <div v-for="i in ObjectsUrl">
            <img v-for="p in ObjectsUrl" :src="p" />
        </div>
        <pre class="maps">{{mapData}}</pre>
    </div>
</template>

<script>

    import { gameMap } from "../api/gameRepository";
    import DrawMap from "./DrawMap";

    export default {
        name: 'GameMap',
        components: {DrawMap},
        data() {
            return {
                intervalHandle: null,
                mapData: 'test',
                time: gameMap().data.time,
                ObjectsUrl: [
                    'http://i.stack.imgur.com/NGQbN.png'
                ]
            }
        },

        created() {
            this.intervalHandle = setInterval(() => {
                gameMap().then((response) => {
                    this.mapData = '';
                    for (let row in response.data) {
                        for (let col in response.data[row]) {
                            let cell = response.data[row][col];

                            if (cell === 0) {
                                this.mapData += '-'
                            }
                            if (cell === 1) {
                                this.mapData += '&'
                            }
                            if (cell === 2) {
                                this.mapData += 'C'
                            }
                            if (cell === 3) {
                                this.mapData += '@'
                            }

                        }
                        this.mapData += '\n'
                    }
                    // this.mapData = response.data;
                })
            }, 1000);
        },
        destroyed() {
            clearInterval(this.intervalHandle)
        }
    }
</script>

<style>
    .maps {
        font-size: 5px;
        line-height: 5px;
        letter-spacing: 5px;
    }
</style>
