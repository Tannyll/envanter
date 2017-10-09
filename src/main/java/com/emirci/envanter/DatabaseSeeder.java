package com.emirci.envanter;

import com.emirci.envanter.Repository.*;
import com.emirci.envanter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    //private VehicleUsageService service = VehicleUsageService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(EnvanterApplication.class);

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryTypeRepository inventoryTypeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TrademarkRepository trademarkRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public DatabaseSeeder(InventoryRepository inventoryRepository, InventoryTypeRepository inventoryTypeRepository, DepartmentRepository departmentRepository, TrademarkRepository trademarkRepository, UserRepository userRepository, RoleRepository roleRepository) {

        this.inventoryRepository = inventoryRepository;
        this.inventoryTypeRepository = inventoryTypeRepository;
        //this.departmentRepository = departmentRepository;
        this.trademarkRepository = trademarkRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public DatabaseSeeder() {

    }


    @Override
    public void run(String... args) throws Exception {

        Random r = new Random(0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .withLocale(Locale.US);

        if (trademarkRepository.count() == 0)
            getTrademarkSample();

        if (inventoryTypeRepository.count() == 0)
            getInventoryTypeSample();

        if (departmentRepository.count() == 0)
            getDepartmentSample();

        if (roleRepository.count() == 0)
            getRole();

        if (userRepository.count() == 0)
            getUser();



        int barcode = r.nextInt(160 * 150 + 365 * 36501);

        Department department = departmentRepository.findOne((long) 4);
        Trademark trademark = trademarkRepository.findOne((long) 4);
        InventoryType inventoryType = inventoryTypeRepository.findOne((long) 4);

        for (int i = 0; i < 0; i++) {

            Inventory inventory = new Inventory();

            inventory.setDepartments(department);
            inventory.setTrademarks(trademark);
            inventory.setInventoryTypes(inventoryType);

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

            inventoryRepository.save(inventory);

        }

        for (Inventory item : inventoryRepository.findAll()) {
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

        departmentRepository.save(list);


        for (Department department : departmentRepository.findAll()) {
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

        trademarkRepository.save(list);

        for (Trademark trademark : trademarkRepository.findAll()) {
            logger.info(trademark.toString());
        }

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

        inventoryTypeRepository.save(list);

        for (InventoryType inventoryType : inventoryTypeRepository.findAll()) {
            logger.info(inventoryType.toString());
        }
    }

    public void getRole() {
        Role role = new Role();
        role.setRole("USER");

        roleRepository.save(role);


    }

    public void getUser() {
        AppUser user = new AppUser();
        user.setActive(1);
        user.setEmail("serdar@emirci.com");
        user.setName("Serdar");
        user.setLastName("EMIRCI");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        userRepository.save(user);
    }
}
