<template>
    <div class="arena-container">
        <div class="arena-row" v-for="row in cells">
            <span v-for="cell in row">
                <span class="player-name">{{srcByCellCode(cell).name}}</span>
                <img class="arena-cell" :src="srcByCellCode(cell).url"/>
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
            name:{
                type:String,
                default: 'Unknow'
            }
        },
        methods: {
            srcByCellCode(code) {

                if (code === 3) {
                    return {
                       url:require('../../assets/arena/bitcoin.png')
                    }
                } else if (code === 2) {
                    return{
                        url: require('../../assets/arena/pacman.png'),
                        name:this.name

                    }
                }else if (code === 21) {
                    return {
                        url: require('../../assets/arena/pacman-right.png'),
                        name:this.name
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
