package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType

class UserPackman {

    boolean dead

//const
    final BORDERS = 1
    final EMPTYSPACE = 0
    final COINS = 3

    // --------------------------------
    // --------------------------------

    String name
    int PACMAN = 2
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

        map[y][x] = ElementType.PACMAN.value

//        if (this.map[this.x][this.y] == EMPTYSPACE ) {
//            this.map[this.x][this.y] = PACMAN
//        } else {
//            while (this.map[this.x][this.y] != EMPTYSPACE) {
//                this.y -= 1
//                this.x -= 1
//            }
////            this.map[this.x][this.y] = PACMAN
//            while(this.map[this.x][this.y] == COINS | this.map[this.x][this.y] == PACMAN | this.map[this.x][this.y] == BORDERS){
//                this.x+=1
//            }
//            this.map[this.x][this.y] = PACMAN
//        }

    }

    def move(int x, int y, int x1, int y1) {

        if (dead) {
            return false
        }

        if (x1 < 0 || y1 < 0 || x1 >= 64 || y1 >= 64) {
            return false
        }

        def element = ElementType.fromValue(map[y1][x1] as int)

        switch (element) {

            case ElementType.EMPTY:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = ElementType.PACMAN.value
                this.x = x1
                this.y = y1
                break

            case ElementType.COIN:
                map[y][x] = ElementType.EMPTY.value
                map[y1][x1] = ElementType.PACMAN.value
                this.x = x1
                this.y = y1
                rating++
                glrating ++
                onRating(this)
                break

            case ElementType.BORDER:
                dead = true
                map[y][x] = ElementType.EMPTY.value
                break

            case ElementType.PACMAN:
                def opponent = getPacmanByCoordinate(x1, y1) as UserPackman

                if (opponent.rating > this.rating) {
                    dead = true
                    map[y][x] = ElementType.EMPTY.value
                } else {
                    opponent.setDead(true)
                    map[y][x] = ElementType.EMPTY.value
                    map[y1][x1] = ElementType.PACMAN.value
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
