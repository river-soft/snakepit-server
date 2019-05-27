<template>
    <div class="main-container">
        <div v-for="user in users">
            <h5>{{user.name}} -> {{user.global}}</h5>
        </div>
    </div>
</template>

<script>
    import { gameMap } from "../api/gameRepository";
    export default {
        name: "statistics",
        data(){
            return {
                users: [
                    { name: '', global: 0 }
                ]
            }
        },
        created (){
                gameMap().then((response) => {
                    this.users = response.data.users;
                    console.log(response)
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