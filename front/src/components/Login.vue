<template>
    <div id="form-reg">
        <form action="">
            <img src="../assets/user.png">
            <h1>Авторизируйтесь для доступа к карте</h1>
            <input type="text" placeholder="Имя" v-model="user.username">
            <span class="error" v-if="!nameIsValid">Введите Имя</span>
            <span class="error" v-else="nameIsValid"></span>
            <span v-if="error">{{error}}</span>
            <input type="password" placeholder="Пароль" v-model="user.password">
            <span class="error"  v-if="!passIsValid">Введите пароль</span>
            <span class="error"  v-else="passIsValid"></span>
           <button :disabled="!formIsValid"  @click="login">Войти</button>
        </form>
        <span>Еще нет аккаунта? <router-link to="/registration"> регистрация </router-link> </span>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "login",
        data(){
            return {
                user: {username: '', password: ''},
                error: '',
                loggedIn :false,
                userData:'',

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
                console.log(this.loggedIn);
                if(this.formIsValid) {
                    let user = {
                        name: this.user.username,
                        pass:this.user.password
                    };
                    this.error = '';
                    axios.post(`/api/login?username=${this.user.username}&password=${this.user.password}`)
                        .then((response) => {
                            console.log(response);
                            if(response.status === 200){
                                localStorage.setItem('currentUser',JSON.stringify(user));
                                this.$router.push('/arena');
                            }
                            console.log(this.loggedIn)
                        }).catch((e)  => {
                            console.log(e.message);
                    })
                }
            },
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
        margin:5% auto;
        font-family: -apple-system;
        font-size: 1.5em;
        color:#DCDCDC;
        text-align: center;
        border-right:2px solid #DCDCDC;
    }

    #form-reg{
        width:100%;
        height:auto;
        position: relative;
        display: block;
    }
    form{
        background-color: #008080;
        opacity: 0.9;
        box-shadow: 3px 3px 3px rgba(0,0,0,0.3);
        padding-bottom:1%;
        display:flex;
        flex-direction:column;
        align-items: center;
        margin:1% auto;
        padding-top:2%;
        width:45%;
        height:auto;
    }
    form img{
        width:25%;
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
        margin: 3% auto;
        padding-bottom: 1%;
        box-shadow: 0px 5px 0px rgba(0,0,0,0.5);
    }
    input::placeholder{
        color:white;
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
    span{
        color: #dcdcdc;
    }
    span a{
        color:#4682B4;
    }
</style>