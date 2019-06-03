package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType
import spock.lang.Specification

class UserPackmanTest extends Specification {

    def 'Если пекмен перемещается на монету, то рейтинг повышается на 1'() {

        given: 'Карта с монетой слева от пекмена. Глобальный рейтинг пользователя 1'
        def rating = 1
        def pacman = new UserPackman([[3, 0, 0], [0, 0, 0], [0, 0, 0]], 'test', 1, 0, rating)
        pacman.onRating = { UserPackman x -> rating = x.glrating }

        when: 'Делаем шаг в лево'
        pacman.moveLeft()

        then: 'Глобальный рейтинг должен стать 2, а локальный 1'
        pacman.rating == 1
        rating == 2
    }

    def 'Пекмен двигается влево'() {
        given: 'карта, пекмен'
        def rating = 1
        def pacman = new UserPackman([[0, 0, 0], [0, 0, 0], [0, 0, 0]], 'test', 1, 1, rating)
        when: 'Делаем шаг в лево'
        pacman.moveLeft()

        then:
        pacman.x == 0

    }

    def 'Если пекмен перемещается вверх на монету, то рейтинг повышается на 1'() {

        given: 'Карта с монетой вверху от пекмена. Глобальный рейтинг пользователя 1'
        def rating = 1
        def pacman = new UserPackman([[0, 3, 0], [0, 3, 0], [0, 0, 0]], 'test', 1, 2, rating)
        pacman.onRating = { UserPackman x -> rating = x.glrating }

        when: 'Делаем шаг вверх'
        pacman.moveUp()

        then: 'Глобальный рейтинг должен стать 2, а локальный 1'
        pacman.rating == 1
        rating == 2
    }

    def 'Если пекмен появляется на углу сверху, то пакмен появляется ниже на 1'() {
        given: 'Пакмен появляется ниже'
        def mapBord = new GameService()
        def pacman = new UserPackman([[1, 1, 1, 1, 1], [1, 3, 3, 3, 1], [1, 0, 0, 0, 1], [1, 0, 0, 0, 1], [1, 1, 1, 1, 1]], 'test', 1, 1, 0)
        when: 'Пакмен генерится на монете'
        mapBord.start()

        then: 'Глобальный рейтинг должен стать 2, а локальный 1'
        ([[1, 1, 1, 1, 1], [1, 3, 3, 3, 1], [1, 2, 2, 2, 1], [1, 2, 2, 2, 1], [1, 1, 1, 1, 1]])
    }

    def 'Если на пути пекмена есть стена, он не должен менять свою позицию'() {

        given: 'Карта с стеной слева от пекмена. Положение пекмена по центру карты'
        def pacman = new UserPackman([[1, 0, 0], [1, 0, 0], [1, 0, 0]], 'test', 1, 1, 0)

        when: 'Делаем шаг влево'
        pacman.moveLeft()

        then: 'Положение пекмена должно остатся по центру'
        pacman.getY() == 1
        pacman.getX() == 1
    }

    def 'Если на пути пекмена есть стена, он должен умереть'() {

        given: 'Карта с стеной слева от пекмена. Положение пекмена по центру карты'
        def map = [[1, 0, 0], [1, 0, 0], [1, 0, 0]]
        def pacman = new UserPackman(map, 'test', 1, 1, 0)

        when: 'Делаем шаг влево'
        pacman.moveLeft()

        then: 'Положение пекмена должно остатся по центру, пекмен должен исчезнуть из карты'
        pacman.getY() == 1
        pacman.getX() == 1
        map[1][1] == ElementType.EMPTY.value

        when: 'Детаем шаг вверх'
        pacman.moveUp()

        then: 'Пекмен не должен появится на карте'
        map[0][1] == ElementType.EMPTY.value
    }

    def 'Пытаемся заселить пекемена на стену'() {
        given: 'Карта со стеной по центру'
        def map = [[1, 0, 0], [0, 1, 0], [0, 0, 0] , [0,0,0]]

        when: 'Создаем пекмена там где есть стена'
        def pac = new UserPackman(map, 'test', 1, 1, 0)

        then: 'Пекмен должен переместится по диагонали вверх-влево'
        map[1][1] == ElementType.BORDER.value
        map[0][0] == ElementType.BORDER.value
        pac.x == 3
        pac.y == 3
    }
}



