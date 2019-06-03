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
        methods: {
            srcByCellCode(code, x, y) {
                console.log('my name is ' + this.users[0].name);
                console.log(x,y);
                console.log('me pos x : ' +  this.users[0].x + 'me pos y' + this.users[0].y);
                if (code === 3) {
                    return {
                       url:require('../../assets/arena/bitcoin.png')
                    }
                } else if (code === 20) {
                    if (this.users[0].x - 1 === x - 1 ) {
                        return {
                            url: require('../../assets/arena/pacman.png'),
                            name: this.users[0].name
                        }
                    } else {
                        return{
                            url: require('../../assets/arena/pacman.png'),
                            name:''
                        }
                    }
                }else if (code === 21) {
                    if (this.users[0].x + 1 === x + 1 ) {
                        return {
                            url: require('../../assets/arena/pacman-right.png'),
                            name: this.users[0].name
                        }
                    } else {
                        return {
                            url: require('../../assets/arena/pacman-right.png'),
                            name: ''
                        }
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
        margin-left:-1.75%;
        padding-bottom:3px;
    }
</style>
