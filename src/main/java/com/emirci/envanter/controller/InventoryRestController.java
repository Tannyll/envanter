package com.emirci.envanter.controller;

import com.emirci.envanter.model.Inventory;
import com.emirci.envanter.service.InventoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by serdaremirci on 10/31/17.
 */
@Controller
@RequestMapping("/api/inventory")
public class InventoryRestController {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            .withLocale(Locale.US);

    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired(required = true)
    private InventoryService inventoryService;

    public InventoryRestController() {

    }

    @RequestMapping("/list")
    public ResponseEntity<Object> inventoryList() {

        List<JSONObject> entities = new ArrayList<>();

        for (Inventory n : inventoryService.getAll()) {
            JSONObject entity = new JSONObject();
            entity.put("InventoryId", n.getInventoryId());
            entity.put("Barcode", n.getBarcode());
            entity.put("Feature", n.getFeature());
            entity.put("InvoiceDate", df.format(n.getInvoiceDate()));
            entity.put("InvoiceNumber", n.getInvoiceNumber());
            entity.put("Price", n.getPrice());
            entity.put("UsesUser", n.getUsesUser());
            entity.put("InsertDate", df.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(n.getInsertDate()));
            entity.put("Model", n.getModel());
            entity.put("DepartmentName", n.getDepar().getDepartmentName());
            entity.put("Trademark", n.getTrade().getTrademark());
            entity.put("InventoryType", n.getInvtyp().getInventoryType());

            entities.add(entity);
        }

        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

}
