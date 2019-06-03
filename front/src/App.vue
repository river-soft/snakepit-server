<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
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

  body{

  }
#app {
  position: relative;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #4682B4;
}

</style>
