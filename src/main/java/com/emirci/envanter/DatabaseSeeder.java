package com.emirci.envanter;

import com.emirci.envanter.model.*;
import com.emirci.envanter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EnvanterApplication.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private InventoryTypeService inventoryTypeService;
    @Autowired
    private TrademarkService trademarkService;

    public DatabaseSeeder() {

    }


    @Override
    public void run(String... args) throws Exception {

        Random r = new Random(7);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .withLocale(Locale.US);

        if (trademarkService.getAll().size() == 0)
            getTrademarkSample();

        if (inventoryTypeService.getAll().size() == 0)
            getInventoryTypeSample();

        if (departmentService.getAll().size() == 0)
            getDepartmentSample();

        if (roleService.getAll().size() == 0)
            getRoleSample();

        int barcode = r.nextInt(160 * 150 + 365 * 36501);

        Department department = departmentService.get((long) r.nextInt(5));
        Trademark trademark = trademarkService.get((long) r.nextInt(4));
        InventoryType inventoryType = inventoryTypeService.get((long) r.nextInt(5));

        for (int i = 0; i < 0; i++) {

            Inventory inventory = new Inventory();

            inventory.setDepar(department);
            inventory.setTrade(trademark);
            inventory.setInvtyp(inventoryType);

            inventory.setUserId("2");
            inventory.setInsertUserId("15002f34-c877-42de-b8dc-334b1195cd1c");
            inventory.setUsesUser("Serdar EMIRCI");
            inventory.setInsertDate(Calendar.getInstance().getTime());
            inventory.setFeature("i5-2500 CPU @ 3.20GHz");
            inventory.setModel("ZT 320");
            inventory.setInvoiceNumber(Integer.toString(barcode));
            inventory.setInvoiceDate(Calendar.getInstance().getTime());
            inventory.setPrice(r.nextDouble());
            inventory.setBarcode(Integer.toString(barcode));

            inventoryService.saveOrUpdate(inventory);

        }

        for (Inventory item : inventoryService.getAll()) {
            logger.info(item.toString());
        }
    }


    private void getDepartmentSample() {
        List<Department> list = new ArrayList<>();

        list.add(new Department("İnsan Kaynakları"));
        list.add(new Department("Satınalma"));
        list.add(new Department("Müteri Hizmetleri"));
        list.add(new Department("Muhasebe"));
        list.add(new Department("Proje Tasarım"));
        list.add(new Department("Yönetim"));
        list.add(new Department("Montaj"));
        list.add(new Department("Üretim"));
        list.add(new Department("Genel"));
        list.add(new Department("Bilgi Teknolojileri"));
        departmentService.saveOrUpdate(list);

        for (Department department : departmentService.getAll()) {
            logger.info(department.toString());
        }

    }

    private void getTrademarkSample() {
        List<Trademark> list = new ArrayList<>();

        list.add(new Trademark("3M"));
        list.add(new Trademark("Admiral"));
        list.add(new Trademark("Akpolimer"));
        list.add(new Trademark("Atlas"));
        list.add(new Trademark("Arlon"));
        list.add(new Trademark("Albond"));
        list.add(new Trademark("Selemix"));
        list.add(new Trademark("Ibm"));
        list.add(new Trademark("Dell"));
        list.add(new Trademark("HP"));
        list.add(new Trademark("Samsung"));
        list.add(new Trademark("Beko"));
        trademarkService.saveOrUpdate(list);

        for (Trademark trademark : trademarkService.getAll()) {
            logger.info(trademark.toString());
        }

    }

    private void getInventoryTypeSample() {
        List<InventoryType> list = new ArrayList<>();

        list.add(new InventoryType("Masaüstü Bilgisayar"));
        list.add(new InventoryType("Taşınabilir Bilgisayar"));
        list.add(new InventoryType("Yazıcı"));
        list.add(new InventoryType("Tablet Bilgisayar"));
        list.add(new InventoryType("Monitör"));
        list.add(new InventoryType("Fotoğraf Makinası"));
        list.add(new InventoryType("Video Kamera"));
        inventoryTypeService.saveOrUpdate(list);

        for (InventoryType inventoryType : inventoryTypeService.getAll()) {
            logger.info(inventoryType.toString());
        }
    }

    private void getRoleSample() {
        List<Role> list = new ArrayList<>();

        list.add(new Role("USER"));
        list.add(new Role("ADMIN"));
        list.add(new Role("GUEST"));
        roleService.saveOrUpdate(list);

        for (Role role : roleService.getAll()) {
            logger.info(role.toString());
        }
    }


}
