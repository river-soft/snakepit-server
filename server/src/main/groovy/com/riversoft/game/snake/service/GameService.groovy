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
    private List<List> map = []

    //constans
    final COLUMN_COUNT_X = 64
    final COLUMN_COUNT_Y = 64

    @Scheduled(cron = '* * * * * *')
    void gameTick() {
        movePackmans()
    }
    GameService(){
        COLUMN_COUNT_X.times { x->
            def temp = []
            COLUMN_COUNT_Y.times{ y->
                temp.add(1)
            }
            map.add(temp)
        }

        generateCoins()
        generatePackmans()
    }
    void generateCoins(){
        10.times{
            map[new Random().nextInt(COLUMN_COUNT_X)][new Random().nextInt(COLUMN_COUNT_Y)] = 3
        }
    }

    void generatePackmans(){
        def count = 8
        while(count > 0) {
            def indexX = new Random().nextInt(COLUMN_COUNT_X)
            def indexY = new Random().nextInt(COLUMN_COUNT_Y)
            if (map[indexX][indexY] == 0) {
                map[indexX][indexY] = 2
                count--
            }
        }
    }
    void movePackmans(){
        List savePackmans = []
        COLUMN_COUNT_X.times { x ->
            COLUMN_COUNT_Y.times{ y->
                if(map[x][y]==2){
                    savePackmans.add([x:x,y:y])
                }
            }
        }
        log.debug(savePackmans.toString())
        savePackmans.each {
            while(it.y < 64) {
            map[it.x][it.y] = 0
                map[it.x][it.y + 1] = 2
            }
        }
    }

    BattleState getCurrentState() {
        BattleState.NONE
    }



//get ready array for return into gameController
   List<List> getResult() {
          return map

   }
}
