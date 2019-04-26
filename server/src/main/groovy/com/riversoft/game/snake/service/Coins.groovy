package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j

class Coins {

    int coinsX
    int coinsY

    private List<List> map
    Coins(List<List> map,coinsX,coinsY) {
        this.map = map
        this.coinsX = coinsX
        this.coinsY = coinsY
    }

    void generateCoins(){
        def count = 30
        while(count > 0) {
                map[coinsX][coinsY] = 3
                count--

        }
    }
}