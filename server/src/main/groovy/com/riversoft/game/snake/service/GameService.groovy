package com.riversoft.game.snake.service

import com.riversoft.game.snake.data.domain.User
import com.riversoft.game.snake.data.domain.UserRoundInformation
import com.riversoft.game.snake.data.repository.RoundRepository
import com.riversoft.game.snake.data.repository.UserRepository
import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.ClientPosition
import com.riversoft.game.snake.dto.ElementType
import com.riversoft.game.snake.model.GameRezultModel
import com.riversoft.game.snake.data.domain.Round
import com.riversoft.game.snake.model.RoundInfo
import com.riversoft.game.snake.model.RoundPlayerInfo
import com.riversoft.game.snake.model.UserInfo
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct



@Slf4j
@Service
class GameService {


    @Autowired UserRepository userRepository
    @Autowired private SocketService socketService
    @Autowired UserService userService
    @Autowired private Bcryptor bcryptor
    @Autowired RoundRepository roundDataRepository

    private List<List> map = []
    private List<UserPackman> packmansList = []
    private List<Coins> coins = []
    private List<walls> walls = []

    //constans
    final COLUMN_COUNT_X = 64
    final COLUMN_COUNT_Y = 64
    final BORDERS = 1

    final ROUND_BONUS_FIRST = 3
    final ROUND_BONUS_SECOND = 2
    final ROUND_BONUS_THIRD = 1

    int roundId
    boolean roundStarted = false
    @Scheduled(cron = '* * * * * *')
    void gameTick() {
        def kpd = roundDataRepository.findAll().each {it.userRoundInformations.sort {i -> i.kpd}}.reverse()
        log.info('sss' + kpd.userRoundInformations.kpd[0].toString())

        log.info('dsdsdsdsdsd' + packmansList.each {it.answer}.answer.toString())

        log.info ('it is user final points' + userRepository.findAll().each {it.finalResult}.finalResult.toString())
        if (!roundStarted) {
            log.debug("Round don't started")
            return
        }
        movePackmans(socketService.getClientAnswer(
                new ClientMessage(
                        map: map,
                        positions: packmansList.findAll {!it.isDead()}.collect { x ->
                            new ClientPosition(
                                    clientName: x.name,
                                    posX: x.getX(),
                                    posY: x.getY())
                        })
        ))

        // calculate count alive pacmans

        def lifePacmanCount = packmansList.findAll { !it.isDead() }.size()

        if (lifePacmanCount <= 1) {
            if (lifePacmanCount == 1) {
                def p =  packmansList.find {
                    !it.isDead()
                }
                p.glrating += 5
                log.info('i give bonus for ' + p.name.toString())
            }
            roundStarted = false
//            checkFinalUsers()
            generateAll()
        }

        saveRound()

        if (--time < 0) {
            roundStarted = false
//            checkFinalUsers()
            generateAll()
        }
    }

    int time = 0

    @PostConstruct
    void init() {
        generateAll()
    }

    def generateAll() {
        //save round data
        def lastRound = roundDataRepository
                .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, 'roundId')))
                .content
                .find()
        roundId = lastRound ? lastRound.roundId + 1 : 1
        log.info("Start new round $roundId")
        if (lastRound) {
            userRepository.findAll().each {
                it.countMatch++
                log.info('save count match for all users' + it.countMatch.toString())
            }
        }

        // set timer
        this.time = 10

        map = []

        COLUMN_COUNT_X.times { x->
            def temp = []
            COLUMN_COUNT_Y.times{ y->
                if (y > 0  && x > 0 && y < ( COLUMN_COUNT_Y - 1) && x < ( COLUMN_COUNT_X - 1 ) ) {
                    temp.add(0)
                } else {
                    temp.add(BORDERS)
                }
            }
            map.add(temp)
        }
        (0..3).each{
            walls.add(new walls (map,2,2))
        }
        CreateWalls() //add walls in map

        //create coins
        (0..400).each {
            int coinsX = new Random().nextInt(COLUMN_COUNT_X)
            int coinsY = new Random().nextInt(COLUMN_COUNT_Y)
            coins.add(new Coins(map, coinsX, coinsY))
        }

        start()
        roundStarted = true
    }

    def saveRound() {
        def round = roundDataRepository
                .findByRoundId(roundId)
                .orElse(new Round(
                        roundId: roundId,
                        startPacmanCount: packmansList.size(),
                        startRoundDate: new Date()
                ))

        round.endPackmanCount = packmansList.findAll { !it.isDead() }.size()
        round.endCoinsCount = calcCoinsByMap(map)
        round.userRoundInformations = packmansList.sort{ i-> i.kpd }.reverse().collect {
            def roundCount = roundDataRepository
                    .findAll()
                    .collect { x -> x.userRoundInformations.find { i -> i.name = it.name }?.active }
                    .count{ n -> n?:1 }

            new UserRoundInformation (
                    name: it.name,
                    localRating: it.rating,
                    globalRating: it.glrating,
                    lifeTime: 0,
                    dead: it.isDead(),
                    active: it.answer,
                    kpd:(it.glrating?:1 / roundCount),
            )
        }
        log.debug("Save round ${roundId} info ${round}")
        roundDataRepository.save(round)
    }

    private int calcCoinsByMap(List<List> lists) {
        lists
                .collect {
                    it.findAll { x -> x == ElementType.COIN.value }.size()
                }
                .sum() as int
    }

    def start() {
        packmansList = []

        def users = userRepository.findAll()
//        users.each {
//            it.answer = false
//        }
        users.each {
            try {
                int packmansX = new Random().nextInt(COLUMN_COUNT_X)
                int packmansY = new Random().nextInt(COLUMN_COUNT_Y)
                packmansList.add(new UserPackman(map, it.username, packmansX, packmansY, it.rating, it.countMatch,false,Math.round(it.rating / it.countMatch * 100) / 100,0))
            } catch(Exception e) {
                log.info('something  go wrong in generation pacman')
                log.info(e.message,e)
            }
        }
        users.each {userRepository.save(it)}


        packmansList*.onRating = { UserPackman pacman ->

            def user = userRepository.findByUsername(pacman.name).get()
            user.rating = pacman.glrating

            log.info("Save rating ${pacman.glrating} for user ${pacman.name}")
            log.info("Save count Match")
            userRepository.save(user)
        }
        packmansList*.onCountMatch = {UserPackman  pacman ->
            def user = userRepository.findByUsername(pacman.name).get()
            user.countMatch = pacman.countMatch
            log.info("Save count Match")
            userRepository.save(user)
        }
        packmansList*.getPacmanByCoordinate = { x, y ->
            packmansList.find { it.x == x && it.y == y }
        }


    }

//  def checkFinalUsers () {
//
//    def usersCheck = userRepository.findAll()
//    usersCheck.each {
//            it.kpd = Math.round(it.rating / it.countMatch * 100) / 100
//    }
//
//    usersCheck.each {userRepository.save(it)}
//
//     def kpd = roundDataRepository.findAll().each {it.userRoundInformations.sort {i -> i.kpd}}.reverse()
//    usersCheck.each {
//        switch (it.kpd) {
//            case kpd.userRoundInformations.kpd[0]:
//                it.finalResult += 3
//                log.info('the first is ' + it.username.toString())
//                break
//            case kpd.userRoundInformations.kpd[1]:
//                it.finalResult += 2
//                log.info('the second is ' + it.username.toString())
//                break
//            case kpd.userRoundInformations.kpd[2]:
//                it.finalResult += 1
//                log.info('the third is ' + it.username.toString())
//                break
//        }
//    }
//
//}
    void movePackmans(List<Map> answers) {
        packmansList.each { i->
            def answer = answers.find { x -> x.client == i.name }
            if (answer) {
                //increase count of players match if they connect and play
                i.answer = true
                log.debug("Answer from user ${answer.client} - ${answer.data}")
                switch (answer?.data) {
                    case 'right':
                        i.moveRight()
                        log.debug("${i.name} go to the right")
                        break
                    case 'left':
                        i.moveLeft()
                        log.debug ("${i.name} go to the left")
                        break
                    case 'down':
                        i.moveDown()
                        log.debug("${i.name} go to the bottom")
                        break
                    case 'up':
                        i.moveUp()
                        log.debug("${i.name} go to the top")
                        break

                    default:
                        i.moveRight()
                        log.debug("${i.name} go to the top")
                        break
                }
            }
        }
    }
//add walls in map
    void CreateWalls(){
        walls.each {
            // верхние углы и линия
            it.createWallsX(3, 3, 6)
            it.createWallsY(3, 4, 5)
            it.createWallsY(3, 20, 25)// линия верх
            it.createWallsX(3, 60, 6)
            it.createWallsY(3, 55, 5)
            // нижние углы и линия
            it.createWallsX(55, 3, 6)
            it.createWallsY(60, 4, 5)
            it.createWallsY(60, 20, 25) //линия низ
            it.createWallsX(55, 60, 5)
            it.createWallsY(60, 55, 6)
            // круг левый верх
            it.createWallsY(12, 5, 3)
            it.createWallsX(13, 5, 5)
            it.createWallsY(17, 6, 2)
            it.createWallsX(14, 7, 2)
            // круг левый нижний
            it.createWallsY(46, 5, 3)
            it.createWallsX(47, 5, 5)
            it.createWallsY(51, 5, 3)
            it.createWallsX(48  ,7, 2)
            it.createWallsY(51, 6, 2)
            // круг правый верх
            it.createWallsY(12, 56, 3)
            it.createWallsX(14, 56, 2)
            it.createWallsY(17, 56, 2)
            it.createWallsX(13, 58, 5)
            // круг правый низ
            it.createWallsY(46, 56, 3)
            it.createWallsX(48, 56, 2)
            it.createWallsY(51, 56, 2)
            it.createWallsX(47, 58, 5)
            // левая линия
            it.createWallsX(25,6,14)
            // правая линия
            it.createWallsX(25,57,14)
            // верхний маленький лаб
            it.createWallsY(11,25,15) // центр лния
            it.createWallsY(8,16,15) // левая от центра
            it.createWallsY(8,34,15) // правая от центра
            it.createWallsX(9,16,5) // левая сторона вертикаль
            it.createWallsX(9,48,5) // правая сторона вертикаль
            it.createWallsY(14,16,33) // низ центр
            // низ маленький лаб
            it.createWallsY(52,25,15) // центр лния
            it.createWallsY(55,16,15) // левая от центра
            it.createWallsY(55,34,15) // правая от центра
            it.createWallsX(50,16,5) // левая сторона вертикаль
            it.createWallsX(50,48,6) // правая сторона вертикаль
            it.createWallsY(49,16,33) // низ центр
            // внутриний круг
            // левая часть
            it.createWallsY(19,15,14)// верх левая часть
            it.createWallsX(18,29,3)// вертикаль 3 блока преграда
            it.createWallsX(20,15,9) //низ левая часть
            it.createWallsY(29,14,3) // горизонталь 3 блока преграда низ
            it.createWallsY(29,19,3) // горизонталь 3 блока преграда низ 2
            it.createWallsX(20,20,9) // низ левая часть 2
            // правая часть
            it.createWallsX(18,34,3) //вертикаль 3 блока преграда
            it.createWallsY(19,35,15) //верх правая часть
            it.createWallsX(20,49,9) // правая часть вертикаль
            it.createWallsX(20,44,9) // левая часть
            it.createWallsY(29,43,3) // горизонталь 3 блока 1
            it.createWallsY(29,48,3)  // горизонталь 3 блока 2
            // низ правая
            it.createWallsY(34,48,3) // горизонталь 3 блока 1
            it.createWallsY(34,43,3) // горизонталь 3 блока 2
            it.createWallsX(35,44,9) //вторая правая
            it.createWallsX(35,49,9) //первая правая
            it.createWallsY(44,35,15) //нижняя
            it.createWallsX(43,34,3)// 3 блокк вертикаль
            //левая низ
            it.createWallsX(43,29,3)// 3 блокк вертикаль
            it.createWallsY(44,15,15) // низ левая большая
            it.createWallsX(35,15,9) // вертикаль 1
            it.createWallsX(35,20,9)// вертикаль 2
            it.createWallsY(34,14,3)// 3 блока горизонталь левая
            it.createWallsY(34,19,3)// 3 блока горизонталь левая 2
            //центр карты
            it.createWallsY(27,25,5)//левая  верх
            it.createWallsX(28,25,2)//вертикаль левая
            it.createWallsX(28,38,2)//вертикаль правая
            it.createWallsY(27,34,5)//правая  верх
            // низ центра
            it.createWallsY(36,34,5)// правая низ
            it.createWallsY(36,25,5)// левая низ
            it.createWallsX(34,38,2)// горизонталь право
            it.createWallsX(34,25,2)// горизонталь лево
            // центр центр
            it.createWallsY(30,29,2)// left
            it.createWallsY(30,32,2)//right
            it.createWallsY(33,29,2)//left bottom
            it.createWallsY(33,32,2)//right bottom
        }

    }

    List<RoundInfo> getRounds() {
        roundDataRepository
                .findAll(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, 'roundId')))
                .collect {
                    def kpd = it.userRoundInformations.sort { i -> i.kpd }.reverse()
                    new RoundInfo (
                            roundId: it.roundId,
                            first: new RoundPlayerInfo(
                                    name: kpd[0]?.name,
                                    kpd:  kpd[0]?.kpd,

                            ),

                            second: new RoundPlayerInfo(
                                    name: kpd[1]?.name,
                                    kpd:  kpd[1]?.kpd,
                            ),

                            third: new RoundPlayerInfo(
                                    name: kpd[2]?.name,
                                    kpd:  kpd[2]?.kpd,
                            ))
                }
    }




//get ready data for return into gameControllers

    GameRezultModel getResult () {
        def currentUserName =  SecurityContextHolder.getContext().authentication?.name ?: 'Unknown'

        def pacmanData = packmansList.find {
            it.name == currentUserName.toString()
        }

        return  new GameRezultModel(
                time        : time,
                map         : map,
                currentUser : new UserInfo(
                        rating: pacmanData?.rating ?: 0,
                        name: currentUserName,
                        x: pacmanData?.x ?: 0,
                        y: pacmanData?.y ?: 0,
                        global: userRepository.findByUsername(currentUserName).get().rating
                ),
                users: packmansList.collect { new UserInfo(
                        name: it.name,
                        rating: it.rating,
                        x: it.x,
                        y: it.y,
                        global: userRepository.findByUsername(it.name).get().rating)
                }
        )
    }


    List<User> getFinalUsers () {

        userRepository
                .findAll()
                .sort { i -> i.finalResult }
                .reverse()
                .take(3)
    }

    @Scheduled(cron = '0 0/5 * * * *')
    void calcFinalResults() {

        def rows = []

        roundDataRepository
                .findAll()
                .each {x ->
                    rows << [ name: x.userRoundInformations[0]?.name, bonus: ROUND_BONUS_FIRST ]
                    rows << [ name: x.userRoundInformations[1]?.name, bonus: ROUND_BONUS_SECOND ]
                    rows << [ name: x.userRoundInformations[2]?.name, bonus: ROUND_BONUS_THIRD ]
                }

        def grouped = rows.groupBy { it.name }


        def bonuses = [:]

        grouped.each {
            bonuses.put(it.key, it.value.sum {x -> x.bonus})
        }

        def users = userRepository.findAll()

        users.each {
            it.finalResult = (bonuses[it.username] as Integer) ?: 0
            userRepository.save(it)
        }
    }
}
