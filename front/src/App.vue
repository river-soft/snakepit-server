<template>
  <div id="app">
    <GameMap />
    <h1>Main page</h1>
    <router-link to="/registration">regs</router-link>
    <router-view></router-view>
  </div>
</template>+

<script>
// import HelloWorld from './components/HelloWorld.vue'
import GameMap from "./components/GameMapView";

export default {
  name: 'app',
  components: {
    GameMap
   // HelloWorld
  }
}
</script>

<style>
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
