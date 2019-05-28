package com.riversoft.game.snake.service

import com.riversoft.game.snake.dto.ElementType
import spock.lang.PendingFeature
import spock.lang.Specification

class PacmanKillTest extends Specification {

    def map = [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0]
    ]

    @PendingFeature
    def 'Пекмен с меньшим рейтингом должен погибнуть когда ходит первым'() {

        given: 'два пекмена расположены под углом друг к другу, у второго рейтинга больше'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0)
        pac2.rating = 1

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac1.isDead()
    }

    @PendingFeature
    def 'Пекмен с меньшим рейтингом должен погибнуть когда ходит вторым'() {

        given: 'два пекмена расположены под углом друг к другу, у второго рейтинга больше'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0)
        pac2.rating = 1

        when: 'второй делает шаг влево, первый делает шаг вверх'
        pac2.moveLeft()
        pac1.moveUp()

        then:
        pac1.isDead()
    }

    @PendingFeature
    def 'Если пекмены с одинаковым рейтингом, то должен погибнуть тот кто ходит вторым'() {

        given: 'два пекмена расположены под углом дкруг к другу'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0)

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac1.isDead()
    }

    @PendingFeature
    def 'Если есть монета на место столкновения то забирает ее выживший пекмен'() {

        given: 'два пекмена расположены под углом дкруг к другу и монета на месте куда прийдут пекмены'
        def pac1 = new UserPackman(map, 'pac1', 1, 1,0)
        def pac2 = new UserPackman(map, 'pac2', 2, 0,0)
        map[1][0] = ElementType.COIN.value

        when: 'первый делает шаг вверх, второй делает шаг влево'
        pac1.moveUp()
        pac2.moveLeft()

        then:
        pac2.isDead()
        pac1.rating == 1
    }
}
