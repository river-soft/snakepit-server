package com.riversoft.game.snake.service

class walls {

    private List<List> map
    int x
    int y
    int xWalls = 1
    int yWalls = 1

    walls(List<List> map, int x , y){
        this.map = map
        this.x = x
        this.y = y
    }

    def createwalls(){
        yWalls = 0
        6.times {
            map[x + xWalls][y] = 1
            map[x][y + yWalls] = 1
            xWalls++
            yWalls++
        }
        xWalls = 18
        15.times{
            map[x + xWalls][y] = 1
            xWalls++
        }
    }
}
