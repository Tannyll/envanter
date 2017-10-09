package com.emirci.envanter.controller;

import com.emirci.envanter.Repository.InventoryRepository;
import com.emirci.envanter.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serdaremirci on 9/18/17.
 */

@RestController
public class InventoryRestController {


    private InventoryRepository repo;

    public InventoryRestController(InventoryRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/getBy/{id}", method = RequestMethod.GET)
    public Inventory getBy(@PathVariable("id") Long id) {

        return repo.findOne(id);
        //repo.findByInventoryTypeId("5");
    }

    @RequestMapping(value = "/Inventory/{id}", method = RequestMethod.GET)
    public ResponseEntity Inventory(@PathVariable("id") Long id) {

        return new ResponseEntity(repo.findOne(id), HttpStatus.OK);

    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public @ResponseBody List<Inventory> getAll() {

        return repo.findAll();


    }




}
