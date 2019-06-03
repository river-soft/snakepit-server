package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType
import groovy.util.logging.Slf4j

import javax.annotation.PostConstruct

@Slf4j
class UserPackman {

    boolean dead

//const
    final BORDERS = 1
    final EMPTYSPACE = 0
    final COINS = 3

    // --------------------------------
    // --------------------------------
    String name
    int x
    int y
    public int rating = 0
    public  int glrating = 0
    // --------------------------------
    // --------------------------------


    def onRating = {UserPackman pacman}
    def getPacmanByCoordinate = {int x, int y -> }

    private List<List> map

    UserPackman(List<List> map, String name, int x, int y, int rating) {

        this.glrating = rating
        this.map = map
        this.name = name
        this.x = x
        this.y = y

        if (map[y][x] == EMPTYSPACE ) {
            map[y][x] = ElementType.PACMAN.value

        } else  {
            while (map[y][x] != EMPTYSPACE) {

                y -= 1
                x -= 1
            }

            this.y = y
            this.x = x

            map[y][x] = ElementType.PACMAN.value

        }

    }


    def move(int x, int y, int x1, int y1) {

        if (dead) {
            return false
        }

        if (x1 < 0 || y1 < 0 || x1 >= 63 || y1 >= 63) {
            return false
        }

        def element = ElementType.fromValue(map[y1][x1] as int)

        def pacman = x != x1 ? ElementType.PACMANRIGHT : ElementType.PACMAN

        switch (element) {

            case ElementType.EMPTY:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = pacman.value
                this.x = x1
                this.y = y1
                break

            case ElementType.COIN:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = pacman.value
                this.x = x1
                this.y = y1
                rating++
                glrating ++
                onRating(this)
                break

            case ElementType.BORDER:
                dead = true
                log.info('i dead')
                map[y][x] = ElementType.EMPTY.value
                log.info('i am empty')
                break

            case ElementType.PACMAN:
            case ElementType.PACMANRIGHT:
                def opponent = getPacmanByCoordinate(x1, y1) as UserPackman

//                if (ElementType.isPacman(ElementType.fromValue(map[1][2] as int))) {
//
//                }

                if (opponent.rating > this.rating) {
                    dead = true
                    map[y][x] = ElementType.EMPTY.value
                } else {
                    opponent.setDead(true)
                    map[y][x] = ElementType.EMPTY.value
                    map[y1][x1] = pacman.value
                }
                break
        }

        true
    }

    def moveLeft() {
        move(x, y, x - 1, y)
    }

    def moveRight() {
        move(x, y, x + 1, y)
    }

    def moveUp() {
          move(x, y, x, y - 1)

    }

    def moveDown() {
        move(x, y, x, y + 1)
    }
}
