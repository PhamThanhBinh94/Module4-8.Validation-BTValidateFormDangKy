package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.model.UserValidator;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FormController {
    @Autowired
    private UserService userService;

    @GetMapping("/create-user")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/user/index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/user/index");
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return modelAndView;
        } else {
            userService.save(user);
            modelAndView.addObject("user", new User());
            modelAndView.addObject("message", "New user created successfully");
        }
        return modelAndView;
    }
}
