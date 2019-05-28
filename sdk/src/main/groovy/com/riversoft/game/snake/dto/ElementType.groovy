package com.riversoft.game.snake.dto

enum ElementType {
    BORDER(1),
    EMPTY(0),
    PACMAN(20),
    PACMANRIGHT(21),
    COIN(3)

    int value


    ElementType(int value) {
        this.value = value
    }

    static ElementType fromValue(int value) {

        ElementType
                .values()
                .find { it.value == value }
    }

    static boolean isPacman (ElementType type) {
        [PACMAN,PACMANRIGHT].contains(type)
    }
}
