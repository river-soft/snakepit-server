<template>
    <div class="arena-container">
        <div class="arena-row" v-for="row in cells">
            <img class="arena-cell" :src="srcByCellCode(cell)" v-for="cell in row" />
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Arena',
        props: {
            cells: {
                type: Array,
                default: [
                    [1,1,1,1,1,1],
                    [1,0,0,3,0,1],
                    [1,0,0,0,0,1],
                    [1,2,0,0,0,1],
                    [1,1,1,1,1,1]
                ]
            }
        },
        methods: {
            srcByCellCode(code) {

                if (code === 3) {
                    return require('../../assets/arena/bitcoin.png')
                } else if (code === 2) {
                    return require('../../assets/arena/pacman.png')
                } else if (code === 1) {
                    return require('../../assets/arena/tile.png')
                } else {
                    return require('../../assets/arena/selector-square-border.png')
                }
            }
        }
    }
</script>

<style>
    .arena-row {
        margin: 1px;
        padding: 0;
        height: 16px;
    }
    .arena-cell {
        margin: 1px;
        padding: 0;
    }
</style>
