package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class UserPackman {


//const
    final BORDERS = 1
    final EMPTYSPACE = 0
    final PACMAN = 2
    final COINS = 3

    // --------------------------------
    // --------------------------------

    String name
    int x
    int y
    public int rating = 0
    // --------------------------------
    // --------------------------------


    def onRating = {UserPackman pacman}
//delete it
    private List<List> map

    UserPackman(List<List> map, String name, int x, y,rating) {
        this.rating = rating
        this.map = map
        this.name = name
        this.x = x
        this.y = y
        if(this.map[this.x][this.y] == EMPTYSPACE ){
            this.map[this.x][this.y] = PACMAN
        }else {
          while(this.map[this.x][this.y] != EMPTYSPACE){
              this.y -=  1
              this.x -=  1
          }
            this.map[this.x][this.y] = PACMAN
        }
    }

    def moveLeft() {
        if (map[x][y - 1] != BORDERS) {
            map[x][y] = EMPTYSPACE
            y -= 1
            map[x][y] = PACMAN
        }
        if (map[x][y - 1] == COINS) {
            map[x][y] = EMPTYSPACE
            map[x][y] = PACMAN
            rating++
            onRating(this)
        }
    }

    def moveRight() {
        if (map[x][y + 1] != BORDERS) {
            map[x][y] = EMPTYSPACE
            y += 1
            map[x][y] = PACMAN
        }
        if(map[x][y + 1] == COINS) {
            map[x][y] = EMPTYSPACE
            map[x][y] = PACMAN
            rating++
            onRating(this)
        }
    }

    def moveUp() {
        if (map[x - 1][y] != BORDERS) {
            map[x][y] = EMPTYSPACE
            x -= 1
            map[x][y] = PACMAN
        }
        if (map[x - 1][y] == COINS) {
            map[x][y] = EMPTYSPACE
            map[x][y] = PACMAN
            rating++
            onRating(this)
        }

    }

    def moveDown() {
        if (map[x + 1][y] != BORDERS) {
            map[x][y] = EMPTYSPACE
            x += 1
            map[x][y] = PACMAN
        }
        if (map[x + 1][y] == COINS) {
            map[x][y] = EMPTYSPACE
            map[x][y] = PACMAN
            rating++
            onRating(this)
        }

    }

}