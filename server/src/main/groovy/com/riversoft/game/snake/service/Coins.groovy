package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

class Coins {

    final BORDERS = 1

    int coinsX
    int coinsY

    public List<List> map
    Coins(List<List> map,coinsX,coinsY) {
        this.map = map
        this.coinsX = coinsX
        this.coinsY = coinsY
    }

    def generateCoins(){
            if(map[coinsX][coinsY] != BORDERS ) {
                map[coinsX][coinsY] = 3
            }
         }
     }
