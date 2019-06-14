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
            1
//        gameService.userRepository = Mock(UserRepository)
//        gameService.roundDataRepository = Mock(RoundRepository)
        when:
         gameService
//         gameService.roundDataRepository.findAll() >>  [
//                new Round(roundId: 10, userRoundInformations: [new UserRoundInformation(name: '1',kpd:64), new UserRoundInformation(name: '2',kpd:44), new UserRoundInformation(name: '3',kpd:765),new UserRoundInformation(name: '4')]),
//                new Round(roundId: 11, userRoundInformations: [new UserRoundInformation(name: '2',kpd:28), new UserRoundInformation(name: '1',kpd:34), new UserRoundInformation(name: '4',kpd:2),new UserRoundInformation(name: '3')]),
//                new Round(roundId: 12, userRoundInformations: [new UserRoundInformation(name: '4',kpd:13), new UserRoundInformation(name: '2',kpd:54), new UserRoundInformation(name: '3',kpd:1),new UserRoundInformation(name: '1')]),
//                new Round(roundId: 13, userRoundInformations: [new UserRoundInformation(name: '3',kpd:14), new UserRoundInformation(name: '1',kpd:4), new UserRoundInformation(name: '4', kpd:7),new UserRoundInformation(name: '2')]),
//                new Round(roundId: 14, userRoundInformations: [new UserRoundInformation(name: '2',kpd:19), new UserRoundInformation(name: '4',kpd:74), new UserRoundInformation(name: '1',kpd:9),new UserRoundInformation(name: '3')]),
//                new Round(roundId: 15, userRoundInformations: [new UserRoundInformation(name: '2',kpd:17), new UserRoundInformation(name: '1',kpd:84), new UserRoundInformation(name: '3',kpd:92),new UserRoundInformation(name: '4')]),
//                new Round(roundId: 16, userRoundInformations: [new UserRoundInformation(name: '3',kpd:15), new UserRoundInformation(name: '2',kpd:94), new UserRoundInformation(name: '4',kpd:127),new UserRoundInformation(name: '1')]),
//                new Round(roundId: 17, userRoundInformations: [new UserRoundInformation(name: '4',kpd:10), new UserRoundInformation(name: '1',kpd:104), new UserRoundInformation(name:'3',kpd:165),new UserRoundInformation(name: '2')]),
//                new Round(roundId: 18, userRoundInformations: [new UserRoundInformation(name: '4',kpd:5), new UserRoundInformation(name: '2', kpd:62), new UserRoundInformation(name: '3',kpd:197),new UserRoundInformation(name: '1')]),
//                new Round(roundId: 19, userRoundInformations: [new UserRoundInformation(name: '1',kpd:48), new UserRoundInformation(name: '2',kpd:36), new UserRoundInformation(name: '3',kpd:202),new UserRoundInformation(name: '4')]),
//        ]
//        gameService.calcFinalResults()

        then:
        1 ==  1
    }


}
