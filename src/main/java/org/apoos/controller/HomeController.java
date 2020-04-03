package org.apoos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView goToHome(){
        ModelAndView model = new ModelAndView("index");
        return  model;
    }

    @GetMapping("/accounts")
    public ModelAndView goToAccounts(){
        ModelAndView model = new ModelAndView("accounts");
        return  model;
    }

    @GetMapping("/branches")
    public ModelAndView goToBranches(){
        ModelAndView model = new ModelAndView("branches");
        return  model;
    }

}
