package org.daistudy.springmvc.hello.controller;

import org.daistudy.springmvc.hello.service.HelloService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现 Controller 的接口，只能处理一个请求，所以在 spring-servlet.xml 中 HelloController 只会对应一个 url
 */
@org.springframework.stereotype.Controller("/hello")
public class HelloController implements Controller {
    @Resource
    private HelloService helloService;

    /**
     * 请求处理接口
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @return 返回值是一个 ModelAndView，Model 相当于是我们的数据模型，View 是我们的视图
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        final ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("msg", helloService.hello("world"));
        return modelAndView;
    }
}
