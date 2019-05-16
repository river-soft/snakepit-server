package com.riversoft.game.snake.service

import spock.lang.Specification

class UserPackmanTest extends Specification {

    def 'Единица должна равнятся единице'() {
        when:
        def a = 1

        then:
        a == 1
    }
}
