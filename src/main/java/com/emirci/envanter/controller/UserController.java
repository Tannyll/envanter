package com.emirci.envanter.controller;


import com.emirci.envanter.model.AppUser;
import com.emirci.envanter.model.Role;
import com.emirci.envanter.service.MessageByLocaleServiceImpl;
import com.emirci.envanter.service.RoleService;
import com.emirci.envanter.service.SecurityService;
import com.emirci.envanter.service.UserService;
import com.emirci.envanter.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by serdaremirci on 9/19/17.
 */
@Controller
@RequestMapping("/account")
public class UserController {

    @Inject
    MessageByLocaleServiceImpl messageByLocaleService;
    @Autowired(required = true)
    private UserService userService;
    @Autowired(required = true)
    private RoleService roleService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    public UserController() {

    }

    //https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView("account/user");

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("account/login");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {


        ModelAndView modelAndView = new ModelAndView();

        AppUser user = new AppUser();
        modelAndView.addObject("formBean", user);
        modelAndView.setViewName("account/registration");
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
            modelAndView.setViewName("account/registration");

        } else {
            Role userRole = roleService.findRoleByRole("USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

            userService.saveUser(user);
            modelAndView.addObject("msg", messageByLocaleService.getMessage("form.data.saved"));
            modelAndView.addObject("formBean", new AppUser());
            modelAndView.setViewName("account/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //AppUser user = userService.findUserByEmail(auth.getName());
        //       modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("account/home");
        return modelAndView;
    }

}
