package com.emirci.envanter.controller;


import com.emirci.envanter.Repository.InventoryRepository;
import com.emirci.envanter.model.AppUser;
import com.emirci.envanter.service.SecurityService;
import com.emirci.envanter.service.UserService;
import com.emirci.envanter.service.impl.MessageByLocaleServiceImpl;
import com.emirci.envanter.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Calendar;

/**
 * c sCreated by serdaremirci on 9/19/17.
 */
@Controller
public class UserController {


    InventoryRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Inject
    MessageByLocaleServiceImpl messageByLocaleService;

    public UserController(InventoryRepository repository) {
        this.repository = repository;

    }

    //https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {


        ModelAndView modelAndView = new ModelAndView();

        AppUser user = new AppUser();
        modelAndView.addObject("formBean", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("formBean") @Valid AppUser user, final BindingResult bindingResult) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("serverTime", Calendar.getInstance().getTime());

        AppUser userExists = userService.findUserByEmail(user.getEmail());

        userValidator.validate(user, bindingResult);

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", messageByLocaleService.getMessage("form.validate.duplicate.username"),
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("formBean", user);
            modelAndView.addObject("msg", messageByLocaleService.getMessage("form.data.NotSaved"));
            modelAndView.setViewName("registration");

        } else {
            userService.saveUser(user);
            modelAndView.addObject("msg", messageByLocaleService.getMessage("form.data.saved"));
            modelAndView.addObject("formBean", new AppUser());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
