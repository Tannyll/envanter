package com.emirci.envanter.controller;

import com.emirci.envanter.model.AppUser;
import com.emirci.envanter.model.Inventory;
import com.emirci.envanter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * .Created by serdaremirci on 9/25/17.
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);


    private InventoryService inventoryService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private InventoryTypeService inventoryTypeService;

    @Autowired
    private TrademarkService trademarkService;

    @Autowired(required = true)
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//private InventoryRepository repo;
    //private TrademarkRepository trademarkRepository;
    //private InventoryTypeRepository inventoryTypeRepository;
    //private DepartmentRepository departmentRepository;

    @Value("${app.name:test}")
    private String message = "message";

    @Inject
    MessageByLocaleServiceImpl messageByLocaleService;

    public InventoryController() {

    }

    @RequestMapping("/list")
    public ModelAndView inventoryList() {

        ModelAndView model = new ModelAndView("inventory/inventoryList");
        model.addObject("message", this.message);
        model.addObject("inventoryList", inventoryService.getAll());

        return model;
    }

    @RequestMapping(value = "/newInventory", method = RequestMethod.GET)
    public ModelAndView newInventory() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("formBean", new Inventory());
        modelAndView.setViewName("update");
        return modelAndView;
    }


    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("inventory/update");

        modelAndView.addObject("formBeanTrademark", trademarkService.getAll());
        modelAndView.addObject("formBeanInventoryType", inventoryTypeService.getAll());
        modelAndView.addObject("formBeanDepartment", departmentService.getAll());
        modelAndView.addObject("formBean", inventoryService.get(id));

        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("formBean") @Valid Inventory inventory, final BindingResult bindingResult) {

        DateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();

        //bindingResult.rejectValue("model", messageByLocaleService.getMessage("inventory.field.model"),"There is already a user registered with the email provided");

        inventory.setInsertDate(date);
        inventory.setUserId("1");
        inventory.setInsertUserId("1");

        ModelAndView modelAndView = new ModelAndView("inventory/inventoryList");

        modelAndView.addObject("serverTime", Calendar.getInstance().getTime());

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("formBean", inventory);
            modelAndView.addObject("msg", messageByLocaleService.getMessage("form.data.NotSaved"));
            return modelAndView;

        } else {
            inventoryService.saveOrUpdate(inventory);

            modelAndView.addObject("msg", messageByLocaleService.getMessage("form.data.saved"));
            modelAndView.addObject("formBean", new AppUser());

            LOGGER.info("Updated the information of the todo entry: {}", inventory);

        }

        modelAndView.setViewName("redirect:/inventory/list");

        return modelAndView;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        Long inventoryId = Long.parseLong(request.getParameter("inventoryId"));

        inventoryService.remove(inventoryService.get(inventoryId));

        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

}

