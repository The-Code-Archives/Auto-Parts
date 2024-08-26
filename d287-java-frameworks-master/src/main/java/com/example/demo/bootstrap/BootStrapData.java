package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;


    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(partRepository.count() == 0 && productRepository.count() == 0) {

            String searchPartName = "Engine";

            OutsourcedPart o = new OutsourcedPart();
            o.setCompanyName("Jimmy's Parts");
            o.setName("Tire");
            o.setInv(70);
            o.setMinInv(7);
            o.setMaxInv(110);
            o.setPrice(110.0);
            o.setId(100L);
            outsourcedPartRepository.save(o);
            OutsourcedPart thePart = null;

            OutsourcedPart eng = new OutsourcedPart();
            eng.setCompanyName("Jimmy's Parts");
            eng.setName("Engine");
            eng.setInv(5);
            eng.setMinInv(1);
            eng.setMaxInv(15);
            eng.setPrice(1200.0);
            eng.setId(102);
            outsourcedPartRepository.save(eng);

            OutsourcedPart tran = new OutsourcedPart();
            tran.setCompanyName("Jimmy's Parts");
            tran.setName("Transmission");
            tran.setInv(15);
            tran.setMinInv(4);
            tran.setMaxInv(40);
            tran.setPrice(500);
            tran.setId(103);
            outsourcedPartRepository.save(tran);

            OutsourcedPart ws = new OutsourcedPart();
            ws.setCompanyName("Jimmy's Parts");
            ws.setName("Windshield");
            ws.setInv(20);
            ws.setMinInv(4);
            ws.setMaxInv(80);
            ws.setPrice(100);
            ws.setId(104);
            outsourcedPartRepository.save(ws);

            OutsourcedPart hl = new OutsourcedPart();
            hl.setCompanyName("Jimmy's Parts");
            hl.setName("Headlights");
            hl.setInv(100);
            hl.setMinInv(2);
            hl.setMaxInv(200);
            hl.setPrice(15);
            hl.setId(105);
            outsourcedPartRepository.save(hl);


            List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();

            for (OutsourcedPart part : outsourcedParts) {

                if (part.getName().equals(searchPartName)) {
                    thePart = part;
                    break;
                }

            }

//            System.out.println(thePart.getCompanyName());
            System.out.println(thePart != null ? thePart.getCompanyName() : "No part found");


//        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
//        for(OutsourcedPart part:outsourcedParts){
//            System.out.println(part.getName()+" "+part.getCompanyName());
//        }


        Product corvette= new Product("Corvette Z06",30000.0,5);
        Product mercedes= new Product("Mercedes E55 AMG",25000.0,7);
        Product subaru= new Product("Subaru WRX STI",27000.0,10);
        Product bmw= new Product("BMW M3",45000.0,8);
        Product jeep= new Product("Jeep Trackhawk",35000.0,4);
        productRepository.save(corvette);
        productRepository.save(mercedes);
        productRepository.save(subaru);
        productRepository.save(bmw);
        productRepository.save(jeep);


            System.out.println("Started in Bootstrap");
            System.out.println("Number of Products" + productRepository.count());
            System.out.println(productRepository.findAll());
            System.out.println("Number of Parts" + partRepository.count());
            System.out.println(partRepository.findAll());
        }
    }
}
