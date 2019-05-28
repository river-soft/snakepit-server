<template>
        <div id="main-block">
            <h1>Packman Arena</h1>
              <br>
                <hr>
                    <br>
            <p>Аркадная игра, созданная для любителей программирования  Проверь свои алгоритмы на оптимальность и быстройдествие</p>
            <img alt="" src="../assets/packman.gif">
            <img class="second-img" alt="" src="../assets/packman.gif">
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
            <div class="buttons-panel">
                <button><router-link to="/registration">Регистрация</router-link></button>
                <button><router-link to="/login">Вход</router-link></button>
                <router-view></router-view>
            </div>
        </div>
</template>

<script>
    export default {
        name: "Homepage"
    }
</script>

<style scoped>
    body{

    }
    hr{
        width:25%;
        display: block;
        margin: auto;
        height:5px;
        background-color: #00BFFF;
        outline: 0;
        border: none;
        margin-bottom: 3%;
    }
    ul{
        width:50%;
        margin:10% auto;
    }
    ul li{
        margin:1%;
        width:30px;
        border-radius:50%;
        height:30px;
        display: inline-block;
        animation:fade-out-1 3s;
        transform: translateY(50px);
        /*background-image: url("../assets/coins-homepage.png");*/
        background-size: cover;
    }
    ul li:nth-child(2){
        animation:fade-out-2 3.4s  ;
    }
    ul li:nth-child(3){
        animation:fade-out-3 3.8s  ;
    }
    ul li:nth-child(4){
        animation:fade-out-4 4.2s  ;
    }
    ul li:nth-child(5){
        animation:fade-out-5 4.4s  ;
    }
    h1 {
        font-size: 150px;
        font-family:Roboto;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:white;
    }
    h2{
        font-size: 40px;
        font-family: century;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:#dcdcdc;
    }

    #main-block{
        width:70%;
        margin:auto;
        height:auto;
    }

    #main-block img{
        z-index: 106;
        width:25%;
        float:left;
        margin: 0% 8% ;
        animation-duration: 1.6s;
        animation:slidein 1.6s  ease-in-out;
    }
    #main-block .second-img{
        transform: scale(-1, 1);
        float:right;
        animation:none;
    }
    .buttons-panel{
        width:100%;
        display: flex;
        flex-wrap: wrap;
        height:auto;
        justify-content: center;
    }
    button{
        display: block;
        margin:1%;
        width:20%;
        height:55px;
        background-color: transparent;
        outline: 0;
        color:gray;
        color:white;
        border:none;
        font-size: 1.5em;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        box-shadow: 2px 2px 2px rgba(0,0,0,0.5);
        border-radius:30px;
    }
    button:first-child{
        background-color: #409eff;

    }
    button:last-child{
        background-color: #F08080;
    }
    a{
        text-decoration: none;
        font-style:normal;
        color:white;
    }
    p {
        font-size: 1.9em;
        text-align:center;
        width:60%;
        display:block;
        margin: 3% auto;
        line-height: 1.5;
        padding: 0% 5% 0% 5%;
        font-family:century;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:#dcdcdc;
    }

    @keyframes slidein {
        from {
            transform: translateX(0%);
        }
        to{
            transform: translateX(150%);
            z-index: 102;
        }

    }
    @keyframes fade-out-1{
        from{
            opacity: 1;
        }
        to{
            opacity:-1;
            z-index: 0;
        }
    }

    @keyframes fade-out-2{
        from{
            opacity: 1;
        }
        to{
            opacity:-1;
            z-index: 0;
        }
    }

    @keyframes fade-out-3{
        from{
            opacity: 1;
        }
        to{
            opacity:-1;
            z-index: 0;
        }
    }

    @keyframes fade-out-4{
        from{
            opacity: 1;
        }
        to{
            opacity:-1;
            z-index: 0;
        }
    }

    @keyframes fade-out-5{
        from{
            opacity: 1;
        }
        to{
            opacity:-1;
            z-index: 0;
        }
    }
</style>
