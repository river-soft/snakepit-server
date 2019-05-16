<template>
    <div>
        <arena :cells="mapData"/>
    </div>
</template>
<script>
    import Arena from '../../components/Arena/index';
    import { gameMap } from "../../api/gameRepository";

    export default {
        name: 'ArenaPage',
        components: { Arena },
        data() {
            return {
                mapData: [],
                intervalHandle: null
            }
        },
        created() {
            this.intervalHandle = setInterval(() => {
                gameMap().then((response) => {
                    this.mapData = response.data;
                    console.log("Response new data");
                });
            }, 1000);
        },
        destroyed() {
            clearInterval(this.intervalHandle)
        }
    }
</script>
