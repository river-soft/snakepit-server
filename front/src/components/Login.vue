<template>
    <div id="form-reg">
        <form action="">
            <img src="../assets/user.png">
            <input type="text" placeholder="Имя" v-model="user.username">
            <span class="error" v-if="!nameIsValid">Введите Имя</span>
            <span class="error" v-else="nameIsValid"></span>
            <span v-if="error">{{error}}</span>
            <input type="password" placeholder="Пароль" v-model="user.password">
            <span class="error"  v-if="!passIsValid">Введите пароль</span>
            <span class="error"  v-else="passIsValid"></span>
           <button :disabled="!formIsValid"  @click="login">Войти</button>
        </form>
    </div>
</template>

<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        name: "login",
        data(){
            return {
                user: {username: '', password: ''},
                error: ''

            }
        },computed:{
            nameIsValid(){
                return this.user.username;
            },

            passIsValid(){
                return this.user.password;
            },
            formIsValid(){
                return this.nameIsValid && this.passIsValid
            },

        },methods:{
            login() {

                if(this.formIsValid) {
                    this.error = '';
                    axios.post(`/api/login?username=${this.user.username}&password=${this.user.password}`)
                        .then((response) => {
                            console.log(response);
                            this.$router.push('/arena');
                        }).catch((e)  => {
                            console.log(e.message);
                    })
                }
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
        width:50%;
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
        margin:auto;
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
        text-shadow:0px 3px 0px rgba(0,0,0,0.5);
    }
    button:hover{
        border-color:#409EFF;
        color:white;
        transition:all 0.5s linear;
        background-color:transparent;
    }
    button{
        text-align: center;
        position:relative;
        font-size:1.4em;
        outline:0;
        width:50%;
        color:white;
        margin: 3% auto;
        padding-bottom: 1%;
        height:70px;
        background-color: #409EFF;
        border:2px solid transparent;
        justify-self:center;

    }
    a{
        color:white;
        text-decoration: none;
    }
    .link-to-arena{
        width:100%;
    }
</style>