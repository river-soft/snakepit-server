package com.riversoft.game.snake.service

import com.riversoft.game.snake.data.domain.Round
import com.riversoft.game.snake.data.domain.UserRoundInformation
import com.riversoft.game.snake.data.repository.RoundRepository
import com.riversoft.game.snake.data.repository.UserRepository
import spock.lang.Specification

class GameServiceTest extends Specification {

    GameService gameService = new GameService()

    def 'test'() {

        given:
        gameService.userRepository = Mock(UserRepository)
        gameService.roundDataRepository = Mock(RoundRepository)

        when:
        gameService.roundDataRepository.findAll() >> [
                new Round(roundId: 10, userRoundInformations: [new UserRoundInformation(name: '1'), new UserRoundInformation(name: '2'), new UserRoundInformation(name: '3')]),
                new Round(roundId: 11, userRoundInformations: [new UserRoundInformation(name: '2'), new UserRoundInformation(name: '1'), new UserRoundInformation(name: '3')])
        ]
        gameService.calcFinalResults()

        then:
        4 * gameService.userRepository.save(_)
    }


}
