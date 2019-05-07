package com.riversoft.game.snake.config

import com.riversoft.game.snake.service.SocketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.lang.Nullable
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.HandshakeInterceptor

@Configuration
@EnableWebSocket
class WebSocketConfig implements WebSocketConfigurer {

    @Autowired private SocketService socketService

    @Bean
    TaskScheduler defaultTaskScheduler() {
            ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler()
            threadPoolScheduler.setThreadNamePrefix("web-")
            threadPoolScheduler.setPoolSize(Runtime.getRuntime().availableProcessors())
            threadPoolScheduler.setRemoveOnCancelPolicy(true)
        return threadPoolScheduler
    }

    @Override
    void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketService, '/')
    }
}
