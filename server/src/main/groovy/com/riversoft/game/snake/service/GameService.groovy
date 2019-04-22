package com.riversoft.game.snake.service

import com.riversoft.game.snake.data.repository.UserRepository
import com.riversoft.game.snake.model.BattleState
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Slf4j
@Service
class GameService {

    @Autowired private UserRepository userRepository

    @Scheduled(cron = '* * * * * *')
    void gameTick() {

        log.debug("Calculate game logic")
    }

    BattleState getCurrentState() {
        BattleState.NONE
    }
    static int[] drawArray( int[]arrayname,int countelements){
        arrayname = new int[countelements]
        for(int i=0;i < arrayname.length;i++){
            int result = i + 1

        }
        return result
    }
   static int[] getResult() {
             drawArray(int[]map,int p=64)
       }
}
