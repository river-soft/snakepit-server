package com.riversoft.game.snake.service

import com.riversoft.game.snake.controller.UserController
import com.riversoft.game.snake.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository
    @Autowired
    PasswordEncoder passwordEncoder
    @Autowired
    UserController controller

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRepository
                .findByUsername(username)
                .map { new User(it.username, it.passwordHash, []) }
                .orElseThrow { throw new UsernameNotFoundException('') }
    }

    String getRezult() {
       return userRepository.findAll()
    }

    com.riversoft.game.snake.data.domain.User addNewUser(com.riversoft.game.snake.data.domain.User user) {
        userRepository.save(user)
    }
}


