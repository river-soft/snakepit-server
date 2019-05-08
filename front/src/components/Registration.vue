<template>
    <div id="form-reg">
        <h1>Pizdets, poluchilos</h1>
<!--        <form action="">-->
<!--            <input type="text" placeholder="Имя" v-model="user.username" >-->
<!--            <input type="password" placeholder="Пароль" v-model="user.password">-->
<!--            <input type="button" value ="zui" @click="addToAPI" />-->
<!--        </form>-->
    </div>
</template>
<script>
import axios from 'axios'
    export default {
        name: "form-reg",
        data(){
            return {
                user: {username: '', password: ''},
             }
            },methods:{
            addToAPI(){
                let newuser = {
                   username:this.user.username,
                    password:this.user.password
                }
                // console.log(newuser);
                axios({
                    method:'post',
                    data: {
                        name: 'Siberian Husky'
                    }
                        // .then({
                        //     console:log(newuser)
                        // }).catch(function (e){
                        //     console.log(e)
                        // })
                })
            }

        }
    }



</script>

<style scoped>
h1{
    font-size: 60px;
    color:red;
}
    *{
        margin:0;
        padding:0;
    }
    form{
        justify-content: center;
        align-self: center;
        display:flex;
        flex-wrap: wrap;
        width:40%;
        padding:5%;
        margin:5% auto;
        border:1px solid #4169E1;
        height:500px;
    }
    form input{
        width:70%;
        flex-direction: column;
        border:1px solid #4169E1;
        outline: 0;
        background-color: transparent;
        color:white;
        height:55px;
        margin:1% auto;
        padding-left: 1%;
    }
    button{
        justify-content: center;
        align-self: center;
        width:72%;
        flex-direction: column;
        border:1px solid gray;
        outline: 0;
        background-color: transparent;
        color:white;
        height:55px;
        margin:1% auto;
        padding-left: 1%;
        padding-right: 1%;
    }

</style>