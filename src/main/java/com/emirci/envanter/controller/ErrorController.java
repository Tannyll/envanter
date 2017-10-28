package com.emirci.envanter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by serdaremirci on 10/27/17.
 */

@Controller
public class ErrorController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView("error/403");

        return modelAndView;
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView error404() {
        ModelAndView modelAndView = new ModelAndView("error/404");

        return modelAndView;
    }
}
