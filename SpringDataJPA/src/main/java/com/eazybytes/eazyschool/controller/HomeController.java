package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//@Controller annotation indicates that a particular class serves the role of a controller
@Controller
public class HomeController {


    @RequestMapping(value={"","/","/home"})
    public String displayPage(Model model){
       //Model is an interface inside Spring MVC framework which will act as an
       //Container between your UI and backend code.
//        model.addAttribute("username","John deep");
        return "home.html";
    }

}
