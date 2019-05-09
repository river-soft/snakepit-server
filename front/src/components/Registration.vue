<template>
    <div id="form-reg">
        <h1>Пройдите регистрацию <br> чтобы начать играть</h1>
        <form action="">
            <img src="../assets/user.png">
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
                };
                console.log(newuser);
                axios.post('/api/users', {
                    username: this.user.username,
                    passwordHash: this.user.password
                })
                    .then(function (response) {
                    console.log(response);
                }).catch(function(e){
                    console.log(e)
                })
            }

        }
    }



</script>

<style scoped>
    *{
        margin:0;
        padding:0;
    }
    h1{
        width:30%;
        margin: auto;
        font-family: Ubuntu;
        font-size: 2.5em;
        color:white;
        text-align: center;
        border-right:4px solid #4682B4;
    }
    #form-reg{
        width:100%;
        height:auto;
        position: relative;
        display: block;
     }
form{
    background-color: #222226;
    box-shadow: 3px 3px 3px rgba(0,0,0,0.3);
    padding-bottom:1%;
    display:flex;
    flex-direction:column;
    align-items: center;
    margin:5% auto;
    padding-top:2%;
    width:50%;
    height:auto;
}
    form img{
        width:30%;
        margin: auto;
    }
form input{
    text-align: center;
    position:relative;
    background-color:transparent;
    font-size:1.4em;
    outline:0;
    border:none;
    border-bottom:2px solid #409EFF;
    width:50%;
    height:55px;
    color:white;
    margin: 3% auto;
    padding-bottom: 1%;
    box-shadow: 0px 3px 0px rgba(0,0,0,0.5);
}


form input:last-child:hover{
    border-color:#409EFF;
    color:#252525;
    transition:all 0.5s linear;
    background-color:transparent;
}
form input:last-child{
    height:70px;
    background-color: #409EFF;
    margin-top:2%;
    border:2px solid transparent;
    justify-self:center;
    color:white;
    box-shadow: 1px 1px 1px rgba(0,0,0,0.3);
}

</style>