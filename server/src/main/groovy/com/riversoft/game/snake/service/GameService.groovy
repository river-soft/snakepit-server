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
    private List<UserPackman> packmansList = []
    private List<Coins> coins = []

    //constans
    final COLUMN_COUNT_X = 64
    final COLUMN_COUNT_Y = 64
    final BORDERS = 1




    @Scheduled(cron = '* * * * * *')
    void gameTick() {
        getCoins()
        movePackmans()
        log.debug(packmansList.rating.toString())
    }


    //CONSTRUCTOR FOR THIS CLASS
    GameService(){
        COLUMN_COUNT_X.times { x->
            def temp = []
            COLUMN_COUNT_Y.times{ y->
                if (y > 0  && x > 0 && y < ( COLUMN_COUNT_Y - 1 ) && x < ( COLUMN_COUNT_X - 1 ) ) {
                    temp.add(0)
                } else {
                    temp.add(BORDERS)
                }
            }
            map.add(temp)
        }
        (0..7).each {
            //create coords for packmansList
            int packmansX = new Random().nextInt(COLUMN_COUNT_X)
            int packmansY = new Random().nextInt(COLUMN_COUNT_Y)
                if(packmansY > BORDERS && packmansX > BORDERS && packmansY < COLUMN_COUNT_Y && packmansX < COLUMN_COUNT_X) {
                    packmansList.add(new UserPackman(map, it.toString(), packmansX,packmansY))
                }
        }
        (0..29).each {
            int coinsX = new Random().nextInt(COLUMN_COUNT_X)
            int coinsY = new Random().nextInt(COLUMN_COUNT_Y)
                if(coinsY > BORDERS && coinsX > BORDERS && coinsY < COLUMN_COUNT_Y && coinsX < COLUMN_COUNT_X) {
                    coins.add(new Coins(map, coinsX, coinsY))
                }
        }

    }



    //Create coins for packmans
//    void generateCoins(){
//        def count = 30
//        while(count > 0) {
//            def indexX = new Random().nextInt(COLUMN_COUNT_X)
//            def indexY = new Random().nextInt(COLUMN_COUNT_Y)
//            if (map[indexX][indexY] == 0) {
//                map[indexX][indexY] = 3
//                count--
//            }
//        }
//    }

//    //create packmans for each user
//    void generatePackmans(){
//        def count = 8
//        while(count > 0) {
//            def indexX = new Random().nextInt(COLUMN_COUNT_X)
//            def indexY = new Random().nextInt(COLUMN_COUNT_Y)
//            if (map[indexX][indexY] != BORDERS) {
//                map[indexX][indexY] = 2
//                count--
//            }
//        }
//    }
    //save coordinates packmans and move packmans
    void movePackmans(){
        packmansList.each { i->
            switch (0) {
                case 0:
                    i.moveRight()
                    break
                case 1:
                    i.moveLeft()
                    break
                case 2:
                    i.moveDown()
                    break
                case 3:
                    i.moveUp()
                    break
            }
        }
    }

    void getCoins(){
        coins.each{
            it.generateCoins()
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
