<template>
    <div class="arena-container">
        <div class="arena-row" v-for="(row, x) in cells">
            <span v-for="(cell, y) in row">
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
        methods: {
            srcByCellCode(code, x, y) {

                console.log(x);
                if (code === 3) {
                    return {
                       url:require('../../assets/arena/bitcoin.png')
                    }
                } else if (code === 2) {
                    return{
                        url: require('../../assets/arena/pacman.png'),
                        name: this.users[0].name

                    }
                }else if (code === 21) {
                    return {
                        url: require('../../assets/arena/pacman-right.png'),
                        name: this.users[0].name
                    }

                } else if (code === 1) {
                    return {
                        url: require('../../assets/arena/tile.png')
                    }
                } else {
                    return {
                        url: require('../../assets/arena/selector-square-border.png')
                    }
                }
            }
        }
    }
</script>

<style>
    .arena-row {
        position: relative;
        margin: 1px;
        padding: 0;
        height: 16px;
    }
    .arena-cell {
        position: relative;
        margin:2px;
        padding: 0;
    }
    .player-name {
        width:auto;
        height:10px;
        position:absolute;
        z-index:105;
        text-align: center;
        font-size: 10px;
        top:20px;
        border-bottom: 1px solid #4682B4;
        color:white;
        margin-left:-1.75%;
        padding-bottom:3px;
    }
    .arena-container{
       width:70%;
        margin:0% 15% auto;
    }
</style>
