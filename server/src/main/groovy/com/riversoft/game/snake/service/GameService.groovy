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
    private List<walls> walls = []

    //constans
    final COLUMN_COUNT_X = 64
    final COLUMN_COUNT_Y = 64
    final BORDERS = 1




    @Scheduled(cron = '* * * * * *')
    void gameTick() {
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
        //create coins
        (0..50).each {
            int coinsX = new Random().nextInt(COLUMN_COUNT_X)
            int coinsY = new Random().nextInt(COLUMN_COUNT_Y)
                if(coinsY > BORDERS && coinsX > BORDERS && coinsY < COLUMN_COUNT_Y && coinsX < COLUMN_COUNT_X) {
                    coins.add(new Coins(map, coinsX, coinsY))
                }
        }
        (0..3).each{
            walls.add(new walls (map,2,2))
        }
        getCoins()//add coins in map
        CreateWalls()
    }




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
//add coins in map
    void getCoins(){
        coins.each{
            it.generateCoins()
        }
    }


//add walls in map
    void CreateWalls(){
        walls.each{
            it.createWallsX(5,5,10)
            it.createWallsY(5,5,20)
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
