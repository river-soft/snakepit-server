<template>
    <div>
        <br>
        <br>
        <br>
        <h1>Лидеры</h1>
        <br>
            <ul>
                <li> {{users[0].username}}</li>
                <li> {{users[1].username}}</li>
                <li> {{users[2].username}}</li>
            </ul>
    </div>

    
</template>

<script>
    import { getFinalUser} from "../api/finalResults";
    export default {
        name: "finalResults",
        data () {
            return {
                users: []
            }
        },
            created(){
                getFinalUser().then((response) => {
                    console.log(response.data);
                    this.users = response.data;
                })
                    .catch((e) => {
                        console.log(e.message)
                    })
            }
    }
</script>

<style scoped>
    h1 {
        width:100%;
        font-size: 50px;
        font-family:Roboto;
        text-shadow:1px 4px 1px rgba(0,0,0,0.5);
        color:#b3c5d3;
        padding-bottom: 3%;
    }
    li {
        width:30%;
        display:block;
        background-color: #c7d6e2;
        color:#566b7c;
        padding: 1%;
        margin: 2% auto;
        text-align: center;
        font-size: 1.4em;
        box-shadow: 1px 4px 1px rgba(0,0,0,0.2);
    }
</style>