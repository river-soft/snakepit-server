<template>
    <pre class="maps">{{mapData}}</pre>
</template>

<script>
    import { gameMap } from "../api/gameRepository";

    export default {
        name: 'GameMap',
        data() {
            return {
                intervalHandle: null,
                mapData: 'test'
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
                                this.mapData += ' '
                            }
                            if (cell === 1) {
                                this.mapData += '*'
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
        font-size: 10px;
        line-height: 10px;
        letter-spacing: 10px;
    }
</style>
