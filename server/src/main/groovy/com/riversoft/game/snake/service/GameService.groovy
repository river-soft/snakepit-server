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
    final BORDERS = 1

//    @Scheduled(cron = '* * * * * *')
//    void gameTick() {
//        movePackmans()
//    }
    //CONSTRUCCTOR FOR THIS CLASS
    GameService(){
        COLUMN_COUNT_X.times { x->
            def temp = []
            COLUMN_COUNT_Y.times{ y->
                if (y > 0  && x > 0 && y < ( 64 - 1 ) && x < ( 64 - 1 ) ) {
                    temp.add(0)
                } else {
                    temp.add(BORDERS)
                }
            }
            map.add(temp)
        }
        generatePackmans()
        generateCoins()
//        eatCoins()
    }
    //Create coins for packmans
    void generateCoins(){
            def count = 30
            while(count > 0) {
                def indexX = new Random().nextInt(COLUMN_COUNT_X)
                def indexY = new Random().nextInt(COLUMN_COUNT_Y)
                if (map[indexX][indexY] == 0) {
                    map[indexX][indexY] = 3
                    count--
                }
            }
        }

    //create packmans for each user
    void generatePackmans(){
        def count = 8
        while(count > 0) {
            def indexX = new Random().nextInt(COLUMN_COUNT_X)
            def indexY = new Random().nextInt(COLUMN_COUNT_Y)
            if (map[indexX][indexY] != BORDERS) {
                map[indexX][indexY] = 2
                count--
            }
        }
    }
    //save coordinates packmans and move packmans
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
            if (it.y < (COLUMN_COUNT_Y -2) && it.x < (COLUMN_COUNT_X - 2 ) && it.y > 1 && it.x > 1) {
                map[it.x][it.y] = 0
                map[it.x + 1][it.y] = 2
            }else{
                map[it.x][it.y] = map[it.x][it.y]
            }
        }
    }

    //crash packman in borders
//    void eatCoins(){
//        COLUMN_COUNT_X.times { x->
//            COLUMN_COUNT_Y.times { y->
//                savePackmans.each{
//                    if(map[it.x][it.y] == 3){
//                        map[it.x][it.y] = 2
//                        generateCoins()
//
//                   }
//
//                }
//            }
//        }
//    }
    BattleState getCurrentState() {
        BattleState.NONE
    }



//get ready array for return into gameController
   List<List> getResult() {
          return map

   }
}
