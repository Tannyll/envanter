package com.emirci.envanter;

import com.emirci.envanter.model.Department;
import com.emirci.envanter.model.Inventory;
import com.emirci.envanter.model.InventoryType;
import com.emirci.envanter.model.Trademark;
import com.emirci.envanter.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    //private VehicleUsageService service = VehicleUsageService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(EnvanterApplication.class);

    @Autowired
    private InventoryService inventoryService;

    public DatabaseSeeder() {

    }


    @Override
    public void run(String... args) throws Exception {

        Random r = new Random(0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .withLocale(Locale.US);

/*        if (trademarkRepository.count() == 0)
            getTrademarkSample();*/

/*        if (inventoryTypeRepository.count() == 0)
            getInventoryTypeSample();*/

/*        if (departmentRepository.count() == 0)
            getDepartmentSample();*/


/*        if (userService.findUserByEmail("serdar@emirci.com") == null)
            getUser();*/


        int barcode = r.nextInt(160 * 150 + 365 * 36501);

        //Department department = departmentRepository.findOne((long) 4);
        //Trademark trademark = trademarkRepository.findOne((long) 4);
        //InventoryType inventoryType = inventoryTypeRepository.findOne((long) 4);

        for (int i = 0; i < 0; i++) {

            Inventory inventory = new Inventory();

            //inventory.setDepartments(department);
            //inventory.setTrademarks(trademark);
            //inventory.setInventoryTypes(inventoryType);

            inventory.setUserId("2");
            inventory.setInsertUserId("15002f34-c877-42de-b8dc-334b1195cd1c");
            inventory.setUsesUser("Serdar EMIRCI");
            inventory.setInsertDate(Calendar.getInstance().getTime());
            inventory.setFeature("i5-2500 CPU @ 3.20GHz");
            inventory.setModel("DE 323");
            inventory.setInvoiceNumber(Integer.toString(barcode));
            inventory.setInvoiceDate(Calendar.getInstance().getTime());
            inventory.setPrice(1.234);
            inventory.setBarcode(Integer.toString(barcode));

            inventoryService.saveOrUpdate(inventory);


        }

        for (Inventory item : inventoryService.getAll()) {
            logger.info(item.toString());
        }
    }


    private void getDepartmentSample() {
        List<Department> list = new ArrayList<>();

        list.add(new Department("Bilgi Teknolojileri"));
        list.add(new Department("İnsan Kaynakları"));
        list.add(new Department("Satınalma"));
        list.add(new Department("Müteri Hizmetleri"));
        list.add(new Department("Muhasebe"));
        list.add(new Department("Proje Tasarım"));
        list.add(new Department("Montaj"));
        list.add(new Department("Yönetim"));
        list.add(new Department("Montaj"));
        list.add(new Department("Üretim"));
        list.add(new Department("Genel"));

        //departmentRepository.save(list);


  /*      for (Department department : departmentRepository.findAll()) {
            logger.info(department.toString());
        }*/

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

        //trademarkRepository.save(list);

        /*for (Trademark trademark : trademarkRepository.findAll()) {
            logger.info(trademark.toString());
        }*/

    }

    private void getInventoryTypeSample() {
        List<InventoryType> list = new ArrayList<>();

        list.add(new InventoryType("Masaüstü Bilgisayar"));
        list.add(new InventoryType("Taşnabilir Bilgisayar"));
        list.add(new InventoryType("Yazıcı"));
        list.add(new InventoryType("Tablet Bilgisayar"));
        list.add(new InventoryType("Monitör"));
        list.add(new InventoryType("Fotoğraf Makinası"));
        list.add(new InventoryType("Video Kamera"));

        // inventoryTypeRepository.save(list);

     /*   for (InventoryType inventoryType : inventoryTypeRepository.findAll()) {
            logger.info(inventoryType.toString());
        }*/
    }
}
