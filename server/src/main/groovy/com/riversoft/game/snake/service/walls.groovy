package com.riversoft.game.snake.service

class walls {

    private List<List> map
    int x
    int y

    walls(List<List> map, int x, y) {
        this.map = map
        this.x = x
        this.y = y
    }

    def createWallsX(int x, y, count) {
        count.times {
            if(map[x][y] == 0) {
                map[x][y] = 1
                x++
            }

        }
    }

    def createWallsY(int x, y, count) {
        count.times {
            if(map[x][y] == 0) {
                map[x][y] = 1
                y++
            }

        }
    }
}

