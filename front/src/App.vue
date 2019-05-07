<template>
  <div id="app">
    <GameMap />
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import GameMap from "./components/GameMapView";

export default {
  name: 'app',
  components: {
    GameMap,
    HelloWorld
  }
}
export default {
  name: 'formData',
  data()  {
    return  {
      User:{name:'',password:'',repeatPassword:''},
    }
  }, methods:  {
    addToAPI(){
      let newUser = {

      }
    }

  }

}
</script>

<style>
  body{
    background-color: #333336;
  }
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #4682B4;
  margin-top: 60px;
}
  a{
    color:red;
    font-size:20px;
  }
</style>
