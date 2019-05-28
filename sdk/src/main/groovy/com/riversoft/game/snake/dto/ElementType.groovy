package com.riversoft.game.snake.dto

enum ElementType {
    EMPTY(0),
    PACMAN(2),
    COIN(3)

    int value

    ElementType(int value) {
        this.value = value
    }
}
