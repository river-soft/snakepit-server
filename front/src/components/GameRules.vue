<template>
    <div class="txt-wrapper">
        <h1>Правила Pacman Arena</h1>
         <ul>
             <li> <p> <br> Для управления пакменом пользователь пишет программу (алгоритм). Подключение игре происходит через handshake клиентской программы к серверу (Пример написанный на языке программирования «Groovy» : </p> </li>
             <li> <p> <br> <a  href="https://drive.google.com/open?id=1XqWWWlZ6vBeHT6AUyQRa-4KhuwvfVP2w" download>Скачать файлы для начала игры </a>
                     <br>
                     <br>
                     <br>
                 class MyPackmanClient extends PacManClient { <br>
                 MyPackmanClient(String host, String username, String password) { <br>
                 super(host, username, password) <br>
                 } <br>
                 @Override <br>
                 Direction onRequest(ClientMessage message) { <br>
                 // Ваш код здесь пример : <br>
                 // return Direction.Up <br>
                  } <br>
                 } <br>
                 class TestSdkApplication { <br>
                 static void main(String[] args) { <br>
                 new MyPackmanClient('snakepit.westeurope.cloudapp.azure.com', 'Username', 'password') <br>
                     } <br>
                 } <br>
                 </p>
             </li>
             <li> <p> <br> За съедение монеты игрок получает очки рейтинга. </p> </li>
             <li> <p> <br> Общее число рейтинга можно увидеть на странице «Статистика («Глобальный рейтинг»)», а также можно увидеть лидеров (1,2,3 — места (в зависимости от КПД)) на странице «Лидеры». </p> </li>
             <li> <p> <br> КПД рассчитывается по формуле : "Глобальный рейтинг / кол-во сыграных пользователем раундов". <br> </p> </li>
             <li> <p> <br> Игрок делает 1 ход/секунду. </p> </li>
             <li> <p> <br> Сессия длится 2 минуты (120 сек.), а если все пакмены умирают до конца, то сессия заканчивается досрочно. </p> </li>
             <li> <p> <br> При столкновения 2-оих пакменов умирает тот у кого меньше очков локального рейтинга, а если он (рейтинг) одинаковый, то 50/50. </p> </li>
             <li>
                 <p>
                     <br>
                     Карта символов : <br>
                     0 -  Пустота. <br>
                     1 -  Стена. <br>
                     2 -  Pacman. <br>
                     3 -  Coin. <br>
                 </p>
             </li>
         </ul>
    </div>
</template>


<script>
    export default {
        name: "GameRules"
    }
</script>

<style scoped>

    .txt-wrapper{
        width:60%;
        margin: auto;
        display: block;
    }

    h1{
        margin: 5% auto;
        width:100%;
        font-size: 50px;
        font-family:Roboto;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:#b3c5d3;

    }
    li p{
        margin: 2% auto;
        width:100%;
        display:block;
        text-align: justify;
        font-size: 1.4em;
        line-height: 1.8;
        font-style:normal;
        color:#566b7c;
    }

    li:nth-child(2){
        background-color: #e3ecf2;
        padding: 2%;
        text-align: center;
    }
    li:nth-child(2) p {
        margin-left: 15%;
        line-height:1.9;
        color:#FA8072;
    }
    p a {
        color: gray;
        padding: 1%;
        border:1px solid #dcdcdc;
    }

</style>