package com.yimoxiamu.websocketdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebSocketConfig
 * @Description TODO
 * @Author xiamu
 * @Email 768840822@qq.com
 * @Date 2018/8/20 15:56
 * @VERSION 1.0
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
