package com.yimoxiamu.websocketdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexControl
 * @Description TODO
 * @Author xiamu
 * @Email 768840822@qq.com
 * @Date 2018/8/20 14:38
 * @VERSION 1.0
 **/
@Controller
public class IndexControl {

    @RequestMapping(value = "index")
    public String toIndex(){
        return "index";
    }
}
