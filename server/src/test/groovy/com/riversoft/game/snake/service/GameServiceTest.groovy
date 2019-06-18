package com.riversoft.game.snake.service

import com.riversoft.game.snake.data.domain.Round
import com.riversoft.game.snake.data.domain.User
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
        gameService.roundDataRepository.findAll() >>
                gameService.roundDataRepository.save() >>
                [
                        new Round(roundId: 10, userRoundInformations: [new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 11, userRoundInformations: [new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 12, userRoundInformations: [new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 13, userRoundInformations: [new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 14, userRoundInformations: [new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User4')]),
                        new Round(roundId: 15, userRoundInformations: [new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 16, userRoundInformations: [new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User4')]),
                        new Round(roundId: 17, userRoundInformations: [new UserRoundInformation(name: 'User4'), new UserRoundInformation(name: 'User1'), new UserRoundInformation(name: 'User2')]),
                        new Round(roundId: 18, userRoundInformations: [new UserRoundInformation(name: 'User4'), new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 19, userRoundInformations: [new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User4'), new UserRoundInformation(name: 'User3')]),
                        new Round(roundId: 20, userRoundInformations: [new UserRoundInformation(name: 'User2'), new UserRoundInformation(name: 'User4'), new UserRoundInformation(name: 'User3')])

                ]

        gameService.calcFinalResults()

        then:

        gameService.calcFinalResults()
        1 * gameService.roundDataRepository.save(_)


    }
}
