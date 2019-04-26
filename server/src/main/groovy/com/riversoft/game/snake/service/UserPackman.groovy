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

    //--------------------------------
    //--------------------------------


    private List<List> map
    UserPackman(List<List> map, String name, int x,y) {
        this.map = map
        this.name = name
        this.x = x
        this.y = y

    }
    def moveLeft () {
        if( map[x][y - 1] != BORDERS ) {
            map[x][y] = 0
            y -= 1
            map[x][y] = 2
        }
    }
    def moveRight () {
        if( map[x][y + 1] != BORDERS ) {
            map[x][y] = 0
            y += 1
            map[x][y] = 2
        }
    }
    def moveUp () {
        if( map[x - 1][y] != BORDERS ) {
            map[x][y] = 0
            x -= 1
            map[x][y] = 2
        }
    }
    def moveDown () {
        if( map[x + 1][y-1] != BORDERS ) {
            map[x][y] = 0
            x += 1
            map[x][y] = 2
        }
    }
 }