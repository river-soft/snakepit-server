<template>
  <div id="app">
    <GameMap />
    <router-link to="/registration">regs</router-link>
    <router-view></router-view>
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
