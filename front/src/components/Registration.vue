<template>
    <div id="form-reg">
        <h1>Зарегистрируйтесь что бы начать играть</h1>
        <form action="">
            <input type="text" placeholder="Имя" v-model="user.username" >
            <input type="password" placeholder="Пароль" v-model="user.password">
            <input type="password" placeholder="Повторите  Пароль" v-model="user.password">
            <input type="button" value ="Зарегистрироваться" @click="addToAPI" />
        </form>
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
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    font-size: 60px;
    color:#4682B4;
    text-align: center;
}
    *{
        margin:0;
        padding:0;
    }
form{
    border:100px solid #20B2AA;
    padding-bottom:1%;
    display:flex;
    flex-direction:column;
    align-items: center;
    margin:5% auto;
    padding-top:2%;
    width:40%;
    height:auto;
    box-shadow: 1px 2px 2px rgba(0,0,0,0.2);
}
form input{
    position:relative;
    background-color:transparent;
    font-size:1.4em;
    outline:0;
    border:none;
    border-bottom:1px solid black;
    width:50%;
    height:55px;
    color:black;
    margin: 3% auto;
}


form input:last-child:hover{
    color:white;
    transition:0.5s;
    background-color:#20B2AA;
    border-radius:20px;
}
form input:last-child{
    margin-top:5%;
    border:1px solid #20B2AA;
    justify-self:center;
    color:#20B2AA;
}

</style>