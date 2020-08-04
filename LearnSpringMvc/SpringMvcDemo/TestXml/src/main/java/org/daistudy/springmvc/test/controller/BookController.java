package org.daistudy.springmvc.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "addBook";
    }

    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    @ResponseBody
    public void doAdd(String name, String author, Double price, Date date, Boolean ispublic){
        System.out.println(name);
        System.out.println(author);
        System.out.println(price);
        System.out.println(date);
        System.out.println(ispublic);
    }
}
