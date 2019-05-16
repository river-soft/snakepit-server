package com.riversoft.game.snake.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class Rating {


    @Autowired
    private UserPackman packmans

    //Rangs
    String PlayerTitle
    String junior = "Junior"
    String midler = "Midler"
    String higher = "Higher"
    String boss = "Boss"
    String killer = "Killer"
    String god = "God"
    String titan = "Titan"

    //------------------------------------------

    //local variable

    int points = packmans.glrating
    int x
    int y
    List<List> map

    //Constructor for Rating
    Rating(List<List> map , int x , y){
        this.map = map
        this.x = x
        this.y = y
    }

   def rangs(){
       switch (points){
           case 0-5 :
               PlayerTitle = junior
               break
           case 6-10 :
               PlayerTitle = midler
               break
           case 11-15 :
               PlayerTitle = higher
               break
           case 16-20 :
               PlayerTitle = boss
               break
           case 21-30 :
               PlayerTitle = killer
               break
           case 31-39 :
               PlayerTitle = god
               break
           case points  >= 40 :
               PlayerTitle = titan
               break
       }
   }
}
