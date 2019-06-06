<template>
    <div class="main-wrapper">
        <h2>История матчей</h2>
        <br>
        <div class="round-id">
            <h1>№</h1>
            <div class="round-data-id" v-for="round in rounds">{{round.roundId}}</div>
        </div>
        <div class="first">
            <h1>1 место</h1>
            <div class="round-data-id" v-for="round in rounds">{{round.first.name}} : {{round.first.rating}} очков</div>
        </div>
        <div class="second">
            <h1>2 место</h1>
            <div class="round-data-id" v-for="round in rounds">{{round.second.name}} : {{round.second.rating}} очков</div>
        </div>
        <div class="third">
            <h1>3 место</h1>
            <div class="round-data-id" v-for="round in rounds">{{round.third.name}} : {{round.third.rating}} очков</div>
        </div>

    </div>
</template>

<script>
    import { gameMap } from "../api/gameRepository";
    import { HistoryMatches} from "../api/gameRepository";

    export default {
        name: "HistoryMatch",
        data(){
            return {
                users: [
                    { name: '', rating: 0 }
                ]
            }
        },
        created() {
            HistoryMatches().then((response) => {
                this.rounds = response.data;
                console.log("Response new data");
            });
        }
    }
</script>

<style scoped>
    .main-wrapper{
        display: flex;
        flex-wrap:wrap;
        width:80%;
        margin:3% 10%;
    }
    .round-id,.first,.second,.third{
        width:23%;
        flex-direction: column;
        float:left;
        margin:1%;
    }
    h1{
        padding-top: 2%;
        background-color: #c7d6e2;
        padding-bottom: 2%;
        color:#566b7c;
        font-size: 1.6em;

    }
    h2{
        width:100%;
        font-size: 50px;
        font-family:Roboto;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:#b3c5d3;
        padding-bottom: 3%;
    }
    .round-data-id{
        width:100%;
        margin-top: 10%;
        padding-top: 2%;
        border-bottom: 1px solid #dcdcdc;
        padding-bottom: 2%;
        text-align: center;
        color:#586672;
    }

</style>