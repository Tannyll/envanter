package com.emirci.envanter.controller;

import com.emirci.envanter.service.MessageByLocaleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by serdaremirci on 9/18/17.
 */
@Controller
public class HomeController extends AbstractController {

    @Inject
    MessageByLocaleServiceImpl messageByLocaleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        return modelAndView;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("about");

        return modelAndView;
    }

    public String homeTest(@RequestParam(value = "name", required = false, defaultValue = "Wprld") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = {"/welcome"})
    public String welcome(@RequestHeader("Accept-Language") Locale locale) {

        String msg = messageByLocaleService.getMessage("welcome");

        return "welcome";
    }

    //https://github.com/reylibutan/hrms

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "merhaba controller");
        return model;
    }
}
