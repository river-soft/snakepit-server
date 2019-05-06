package com.riversoft.game.snake.service

class walls {

    private List<List> map
    int x
    int y

    walls(List<List> map, int x , y){
        this.map = map
        this.x = x
        this.y = y
    }

    def createwalls(){
        map[x][y] = 1
    }
}
