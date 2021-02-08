package com.apt612.depaybackend.messaging;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
       /* ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1);
        taskScheduler.setThreadNamePrefix("ws-heartbeat-thread-");
        taskScheduler.initialize();
*/
        registry
                .setPreservePublishOrder(true)
                .enableSimpleBroker("/queue","/topic");
             //   .setHeartbeatValue(new long[]{5000, 5000})
//                .setTaskScheduler(taskScheduler);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/websocket").setAllowedOrigins("*");
    }

}
