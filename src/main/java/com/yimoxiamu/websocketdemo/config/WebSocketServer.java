package com.yimoxiamu.websocketdemo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yimoxiamu.websocketdemo.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WebSocketServer
 * @Description WebSocket服务端
 * @Author xiamu
 * @Email 768840822@qq.com
 * @Date 2018/8/20 14:42
 * @VERSION 1.0
 **/
@Component
@ServerEndpoint(value = "/webSocket")
@Slf4j
public class WebSocketServer {
    //计数客户端对象
    private final static AtomicInteger onLineCount=new AtomicInteger(0);
    //存放客户端对象
    //private static CopyOnWriteArraySet<Session> sessionSet=new CopyOnWriteArraySet<>();
    //存放客户端对象
    private static ConcurrentHashMap<String,Session> sessionMap=new ConcurrentHashMap<>();


    /**
     * 客户端连接时
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        sessionMap.put(session.getId(),session);
        int num=onLineCount.incrementAndGet();
        log.info("有连接加入，当前连接数为:"+num);
        JSONObject object=JSONUtil.getJSONObject("server","连接成功",session.getId());
        sendMessage(session,JSON.toJSONString(object));
    }

    @OnClose
    public void onClose(Session session){
    sessionMap.remove(session.getId());
    int num =onLineCount.decrementAndGet();
    log.info("有连接断开，当前连接数为："+num);
    }

    /**
     * 收到客户端发送的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
        JSONObject jsonObject=JSON.parseObject(message);
        if((boolean)jsonObject.get("isAll")){
            log.info("来自客户端"+session.getId()+"群发的消息:"+jsonObject.get("data"));
            for (Session sessions:sessionMap.values()) {
                sendMessage(sessions,(String) jsonObject.get("data"));
            }
        }else{
            log.info("来自客户端"+session.getId()+"单发的消息:"+jsonObject.get("data"));
            sendMessage(session,(String) jsonObject.get("data"));
        }
    }




    /**
     * 服务器向客户端发送消息
     * @param session
     * @param message
     */
    private static void sendMessage(Session session,String message){
    try {
        session.getBasicRemote().sendText(message);
    }catch (IOException e){
        log.debug("发送消息出错:"+e.getMessage());
        e.printStackTrace();
    }
    }


}
