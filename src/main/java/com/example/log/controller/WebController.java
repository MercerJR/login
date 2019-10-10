package com.example.log.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class WebController {
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    @GetMapping("/index")
    public ModelAndView index(String name, String password,HttpServletRequest request) throws UnknownHostException {
        if (StringUtils.isEmpty(name)) {
            name = "匿名用户";
        }
        ModelAndView mav = new ModelAndView("/StudentInfo");
        mav.addObject("name", name);
        mav.addObject("InfoURL","http://"+InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort() + request.getContextPath() + "/show");
        return mav;
    }
}
