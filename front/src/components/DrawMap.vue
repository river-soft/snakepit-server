<template>
    <div>
<!--        <pre class="mapsDraw">{{mapData}}</pre>-->
        <div v-for="p in 64">
            <img v-for="p in ObjectsUrl" :src="p" />
        </div>
    </div>
</template>

<script>
    export default {
        name: "DrawMap",
        data() {
            return {
                donemap: '',
                mapData: 'Drawing...',
                ObjectsUrl:[
                    'https://www.gezginler.net/mobil/resimler/11883/ikon1_pac-man-tournaments-ikon.png',

                ],
            }
        },
        Draw() {
            this.mapData = '';
            for (let row in ObjectsUrl) {
                for (let col in ObjectsUrl[row]) {
                    let cell = ObjectsUrl[row][col];

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
        }
    }
</script>

<style scoped>

</style>