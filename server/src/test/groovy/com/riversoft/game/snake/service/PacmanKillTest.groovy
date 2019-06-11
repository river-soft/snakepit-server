package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType
import spock.lang.Specification

class PacmanKillTest extends Specification {

    def map = [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0]
    ]

    def 'Пекмен с меньшим рейтингом должен погибнуть когда ходит первым'() {

        given: 'два пекмена расположены под углом друг к другу, у второго рейтинга больше'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0,1,false,1,1)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0,1,false,1,1)
        [pac1, pac2]*.getPacmanByCoordinate = {x, y ->
            [pac2, pac1].find { it.x == x && it.y == y}
        }
        pac2.rating = 1

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac1.isDead()
    }

    def 'Пекмен с меньшим рейтингом должен погибнуть когда ходит вторым'() {

        given: 'два пекмена расположены под углом друг к другу, у второго рейтинга больше'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0,1,false,1,1)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0,1,false,1,1)
        [pac1, pac2]*.getPacmanByCoordinate = {x, y ->
            [pac2, pac1].find { it.x == x && it.y == y}
        }
        pac2.rating = 1

        when: 'второй делает шаг влево, первый делает шаг вверх'
        pac2.moveLeft()
        pac1.moveUp()

        then:
        pac1.isDead()
    }

    def 'Если пекмены с одинаковым рейтингом, то должен погибнуть тот кто ходит первым'() {

        given: 'два пекмена расположены под углом друг к другу'
        def pac1 =  new UserPackman(map, 'pac1', 1, 1,0,1,false,1,1)
        def pac2 =  new UserPackman(map, 'pac1', 2, 0,0,1,false,1,1)
        [pac1, pac2]*.getPacmanByCoordinate = {x, y ->
            [pac2, pac1].find { it.x == x && it.y == y}
        }

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac1.isDead()
    }

    def 'Если есть монета на месте столкновения то забирает ее выживший пекмен'() {

        given: 'два пекмена расположены под углом друг к другу и монета на месте куда прийдут пекмены'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0,1,false,1,1)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0,1,false,1,1)
        [pac1, pac2]*.getPacmanByCoordinate = {x, y ->
            [pac2, pac1].find { it.x == x && it.y == y}
        }
        map[0][1] = ElementType.COIN.value
        pac2.rating = 1

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac1.isDead()
        pac2.rating == 1
    }
}
