package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType
import groovy.util.logging.Slf4j


@Slf4j
class UserPackman {

    boolean dead

//const
    final EMPTYSPACE = 0

    // --------------------------------
    // --------------------------------
    String name
    int x
    int y
    public int  rating = 0
    public  int glrating = 0
    public int  countMatch
    // --------------------------------
    // --------------------------------


    def onRating = {UserPackman pacman}
    def onCountMatch = {UserPackman pacman}
    def getPacmanByCoordinate = {int x, int y -> }

    private List<List> map

    UserPackman(List<List> map, String name, int x, int y, int rating, int countMatch) {
        this.glrating = rating
        this.map = map
        this.name = name
        this.x = x
        this.y = y
        this.countMatch = countMatch


        if (map[y][x] == EMPTYSPACE ) {
            map[y][x] = ElementType.PACMAN.value

        } else  {
            while (map[y][x] != EMPTYSPACE) {

                if (y > 0) {
                    y -= 1
                } else {
                    y = map.size() - 1
                }

                if (x > 0) {
                    x -= 1
                } else {
                    x = map[0].size() - 1
                }
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


        def element = ElementType.fromValue(map[y1][x1] as int)

        def pacman = x < x1 ? ElementType.PACMANRIGHT : ElementType.PACMAN

        switch (element) {

            case ElementType.EMPTY:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = pacman.value
                this.y = y1
                this.x = x1
                break

            case ElementType.COIN:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = pacman.value
                this.y = y1
                this.x = x1
                rating++
                glrating++
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
