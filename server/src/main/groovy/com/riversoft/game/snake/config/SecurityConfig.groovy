package com.riversoft.game.snake.config

import com.riversoft.game.snake.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired UserService userService
    @Autowired PasswordEncoder passwordEncoder

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers('/api/game').permitAll()
                .anyRequest().authenticated()

                .and().formLogin()
                .successHandler { req, res, e ->  res.setStatus(200) }
                .failureHandler { req, res, e -> res.setStatus(401) }

//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint { req, res, e -> res.setStatus(401) }

                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf().disable()

        http.httpBasic()

    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder()
    }
}
