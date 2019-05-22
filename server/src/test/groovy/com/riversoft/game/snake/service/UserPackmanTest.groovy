package com.riversoft.game.snake.service

import spock.lang.Specification

class UserPackmanTest extends Specification {

    def 'Если пекмен перемещается на монету, то рейтинг повышается на 1'() {

        given: 'Карта с монетой слева от пекмена. Глобальный рейтинг пользователя 1'
        def rating = 1
        def pacman = new UserPackman([[3,0,0], [0,0,0], [0,0,0]], 'test', 0, 1, rating)
        pacman.onRating = { UserPackman x -> rating = x.glrating }

        when: 'Делаем шаг в лево'
        pacman.moveLeft()

        then: 'Глобальный рейтинг должен стать 2, а локальный 1'
        pacman.rating == 1
        rating == 2
    }
    def 'Пекмен двигается влево'(){
        given:'карта, пекмен'
        def rating = 1
        def pacman = new UserPackman([[0,0,0], [0,0,0], [0,0,0]], 'test', 1, 1, rating)
        when: 'Делаем шаг в лево'
        pacman.moveLeft()

        then:
        pacman.y == 0

    }

    def 'Если пекмен перемещается вверх на монету, то рейтинг повышается на 1'() {

        given: 'Карта с монетой вверху от пекмена. Глобальный рейтинг пользователя 1'
        def rating = 1
        def pacman = new UserPackman([[0,3,0], [0,3,0], [0,0,0]], 'test', 2, 1, rating)
        pacman.onRating = { UserPackman x -> rating = x.glrating }

        when: 'Делаем шаг вверх'
        pacman.moveUp()

        then: 'Глобальный рейтинг должен стать 2, а локальный 1'
        pacman.rating == 1
        rating == 2
    }

    def 'Если на пути пекмена есть стена, он не должен менять свою позицию'() {

        given: 'Карта с стеной слева от пекмена. Положение пекмена по центру карты'
        def pacman = new UserPackman([[1,0,0], [1,0,0], [1,0,0]], 'test', 1, 1, 0)

        when: 'Делаем шаг влево'
        pacman.moveLeft()

        then: 'Положение пекмена должно остатся по центру'
        pacman.getY() == 1
        pacman.getX() == 1
    }

    def 'Единица должна равнятся единице'() {
        when:
        def a = 1

        then:
        a == 1
    }
}
