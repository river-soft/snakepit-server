<template>
    <div class="main-container">
        <p>Имена :{{username}}</p>
        <p>Рейтинг :{{rating}}</p>
    </div>
</template>

<script>
    import { gameMap } from "../api/gameRepository";
    export default {
        name: "statistics",
        data(){
            return{
                username:[],
                rating:[],
            }
        },
        created (){
                gameMap().then((response) => {
                    this.username = response.data.usernames;
                    this.rating = response.data.glratings;
                })
            }
    }
</script>s

<style scoped>
.main-container{
    display: block;
    height: auto;
    color:white;
    margin: 5% auto;
}
</style>