package cn.zsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test")
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        return "Hello World";
    }

    @RequestMapping("/de")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

    @RequestMapping("/in")
    public ModelAndView indexHtml(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

}