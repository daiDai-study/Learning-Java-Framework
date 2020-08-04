package org.daistudy.springmvc.hello.controller;

import org.daistudy.springmvc.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller("/")
public class IndexController implements Controller {
    @Resource
    private HelloService helloService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("msg", helloService.hello("world"));
        return mv;
    }
}
