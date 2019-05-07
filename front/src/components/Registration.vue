<template>
    <div class="form-wrapper">
        <form action="">
            <input type="text" placeholder="Имя" >
            <input type="password" placeholder="Пароль">
            <input type="text" placeholder="Повторите пароль">
            <button>Зарегистрироваться</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: "Registration"
    }
</script>

<style scoped>
    *{
        margin:0;
        padding:0;
    }
    .form-wrapper{
        width:100%;
        height:auto;
        position:relative;
        display:block;
    }
    form{
        justify-content: center;
        align-self: center;
        display:flex;
        flex-wrap: wrap;
        width:40%;
        padding:5%;
        margin:5% auto;
        border:1px solid #DCDCDC;
        height:500px;
    }
    form input{
        width:70%;
        flex-direction: column;
        border:1px solid gray;
        outline: 0;
        background-color: transparent;
        color:#252525;
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
        color:#252525;
        height:55px;
        margin:1% auto;
        padding-left: 1%;
        padding-right: 1%;
    }

</style>