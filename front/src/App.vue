<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
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
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
