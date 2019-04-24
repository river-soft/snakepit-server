package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j

class UserPackman {
    String name
    int rating = 0
    int x = 5
    int y = 5

    private List<List> map
    UserPackman(List<List> map, String name, int x,y) {
        this.map = map
        this.name = name
        this.x = x
        this.y = y

    }
    def moveLeft () {
        map[x][y] = 0
        y -= 1
        map[x][y] = 2
    }
    def moveRight () {
        map[x][y] = 0
        y += 1
        map[x][y] = 2
    }
    def moveUp () {
        map[x][y] = 0
        x -= 1
        map[x][y] = 2
    }
    def moveDown () {
        map[x][y] = 0
        x += 1
        map[x][y] = 2
    }

}