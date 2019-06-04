<template>
    <div class="arena-container">
        <div class="arena-row" v-for="(row,y) in cells">
            <span v-for="(cell, x) in row">
                <span class="player-name">{{srcByCellCode(cell, x, y).name}}</span>
                <img class="arena-cell" :src="srcByCellCode(cell, x, y).url"/>
            </span>


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
            },
            users:{
                type:Array,
                default: []
            }
        },
        data() {
            return {
                assetsMap: {
                    0: require('../../assets/arena/selector-square-border.png'),
                    1: require('../../assets/arena/tile.png'),
                    20: require('../../assets/arena/pacman.png'),
                    21: require('../../assets/arena/pacman-right.png'),
                    3: require('../../assets/arena/bitcoin.png'),
                }
            }
        },
        methods: {
            srcByCellCode(code, x, y) {

                let name = (this.users[0].x === x && this.users[0].y === y) ? this.users[0].name : '';
                let url = this.assetsMap[code];

                return {
                    url: url,
                    name: name
                }
            }
        }
    }
</script>

<style>
    .arena-row {
        width:100%;
        position: relative;
        margin: 2px;
        padding: 0;
        height: 16px;
    }
    .arena-cell {
        position: relative;
        margin:0px 0px 0px 7px;
        padding: 0;
    }
    .player-name {
        width:auto;
        height:10px;
        position:absolute;
        z-index:109;
        text-align: center;
        font-size: 10px;
        top:20px;
        border-bottom: 1px solid #4682B4;
        color:#252525;
        margin-left:-0.3%;
        padding-bottom:3px;
    }
</style>
