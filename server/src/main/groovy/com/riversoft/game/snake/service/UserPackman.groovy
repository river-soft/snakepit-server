package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class UserPackman {


//const
    final BORDERS = 1

    //--------------------------------
    //--------------------------------

    String name
    int x
    int y
    public int rating = 0
    //--------------------------------
    //--------------------------------

//delete it
    private List<List> map

    UserPackman(List<List> map, String name, int x, y) {
        this.map = map
        this.name = name
        this.x = x
        this.y = y

    }

    def moveLeft() {
        if (map[x][y - 1] != BORDERS) {
            map[x][y] = 0
            y -= 1
            map[x][y] = 2
        }
        if (map[x][y - 1] == 3) {
            map[x][y] = 0
            map[x][y] = 2
            rating++
        }
    }

    def moveRight() {
        if (map[x][y + 1] != BORDERS) {
            map[x][y] = 0
            y += 1
            map[x][y] = 2
        }
        if(map[x][y + 1] == 3) {
            map[x][y] = 0
            map[x][y] = 2
            rating++
        }
    }

    def moveUp() {
        if (map[x - 1][y] != BORDERS) {
            map[x][y] = 0
            x -= 1
            map[x][y] = 2
        }
        if (map[x - 1][y] == 3) {
            map[x][y] = 0
            map[x][y] = 2
            rating++
        }

    }

    def moveDown() {
        if (map[x + 1][y] != BORDERS) {
            map[x][y] = 0
            x += 1
            map[x][y] = 2
        }
        if (map[x + 1][y] == 3) {
            map[x][y] = 0
            map[x][y] = 2
            rating++
        }

    }

}