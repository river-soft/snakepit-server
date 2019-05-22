<template>
        <div id="main-block">
          <h1>Packman Arena</h1>
          <img alt="" src="../assets/packman.gif">
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
          <div class="buttons-panel">
           <button><router-link to="/registration">Регистрация</router-link></button>
            <button>Войти</button>
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
    ul{
        width:50%;
        margin:10% auto;
    }
    ul li{
        margin:1%;
        width:20px;
        border-radius:50%;
        height:20px;
        background-color:#4682B4 ;
        display: inline-block;
    }
    h1{
        font-size: 150px;
        font-family:Roboto;
    }

    #main-block{
        width:70%;
        margin: 1% auto;
        height:auto;
    }

    #main-block img{
        width:35%;
        float:left;
        margin: 0 5%;
    }
    .buttons-panel{
        width:100%;
        display: flex;
        flex-wrap: wrap;
        height:auto;
        justify-content: center;
    }
    button{
        margin: 1%;
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
</style>