package com.riversoft.game.snake.service

class walls {

    private List<List> map
    int x
    int y
    int xWalls = 1
    int yWalls = 1

    walls(List<List> map, int x, y) {
        this.map = map
        this.x = x
        this.y = y
    }

    def createWallsX(int x, y, count) {
        count.times {
            map[x][y] = 1
            x++

        }
    }

    def createWallsY(int x, y, count) {
        count.times {
            map[x][y] = 1
            y++

        }
    }
}

