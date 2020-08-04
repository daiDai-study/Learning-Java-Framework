package org.daistudy.springmvc.test.controller;

import org.daistudy.springmvc.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    // 返回 ModelAndView
    @RequestMapping(value = {"/hello", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView hello(){
        final ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", helloService.hello("world"));
        return mv;
    }

    // 返回 void -- 服务端跳转，forward
    @RequestMapping("/hello1")
    public void hello2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/hello.jsp").forward(req, resp);
    }
    // 返回 void -- 临时重定向，redirect 302
    @RequestMapping("/hello2")
    public void hello3(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/jsp/hello.jsp");
    }
    // 返回 void -- 永久重定向，redirect 301
    @RequestMapping("/hello3")
    public void hello4(HttpServletRequest req, HttpServletResponse resp){
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.addHeader("Location", "/jsp/hello.jsp");
    }
    // 返回 void -- 通过 resp 写入
    @RequestMapping("/hello4")
    public void hello5(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.write("hello javaboy");
        pw.flush();
        pw.close();
    }

    // 返回 字符串 -- 返回视图名
    @RequestMapping("/hello11")
    public String hello11(Model model){
        model.addAttribute("msg", helloService.hello("view"));
        return "hello";
    }
    // 返回 字符串 -- 服务端跳转，forward
    @RequestMapping("/hello12")
    public String hello12(Model model){
        model.addAttribute("msg", helloService.hello("forward"));
        return "forward:/jsp/hello.jsp";
    }
    // 返回 字符串 -- 临时重定向，redirect -- Model 如何传递
    @RequestMapping("/hello13")
    public String hello13(Model model){
        model.addAttribute("msg", helloService.hello("redirect 302"));
        return "redirect:/";
    }
    // 返回 字符串 -- 永久重定向，redirect -- Model 如何传递 -- 如何进行永久重定向
    @RequestMapping("/hello14")
    public String hello14(Model model){
        model.addAttribute("msg", helloService.hello("redirect 301"));
        return "redirect:/hello";
    }
    // 返回 字符串 -- 单纯返回字符串 -- Model 没有效果
    @RequestMapping("/hello15")
    @ResponseBody
    public String hello15(Model model){
        model.addAttribute("msg", helloService.hello("responsebody"));
        return "hello";
    }

    // 返回 中文字符串 -- 使用 produces
    @RequestMapping(value = "/hello16",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello5() {
        return "Java 语言程序设计";
    }

}
