<template>
    <div>
        <h3>Турнирная таблица</h3>
    <div class="main-container">
        <span class="container-data-users" v-for="user in users">
            <img src="../assets/user-table.png" alt="">
                <p>{{user.name}} : </p>
                <p>  {{user.global}} очков </p>
            <br>
        </span>
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
                ],
                filterUser:''
            }
        },
        created (){
            gameMap().then((response) => {
                this.users = response.data.users;
                console.log(response)
            })
        },
    }
</script>

<style scoped>
    .main-container{
        display: block;
        height: auto;
        color:white;
        width:100%;
        margin:2% auto;
    }
    .container-data-users {
        position: relative;
        display: flex;
        flex-wrap: wrap;
        flex-direction: column;
        float:left;
        width: 25%;
        margin: 2% 3% auto;
        background-color: rgba(0,128,128,0.8);
        justify-content: center;
        padding: 1% 1% 0% 1%;
        box-shadow: 1px 4px 1px rgba(0,0,0,0.5);
        color: #dcdcdc;
       align-items: center;
    }

    .container-data-users img{
        width:15%;
        position: absolute;
        left:5%;
        top:6%;
        height:75px;
        margin: 1%;
    }

    p{
       font-size: 2em;
        text-align: justify;
    }
    .container-data-users p:first-child{
        padding-bottom: 1%;
    }
    .container-data-users p:last-child{
        padding-top: 2%;
    }

    h3{
        font-size: 3em;
        color:white;
    }

</style>