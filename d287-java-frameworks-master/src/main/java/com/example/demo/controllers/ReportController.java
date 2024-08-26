package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ReportController {

//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ReportController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//    private final ProductService productService;
    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    public ReportController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/generateReport")
    public String generateReport(@RequestParam("productID") Long id, Model model) {
//        logger.info("Generating report for product ID: {} and type: {}", id, type);
        Optional<Product> productOptional = productRepository.findById(id);

//        if (productOptional.isPresent() && "product".equals(type)) {
            Product product = productOptional.get();
            logger.info("Product found: {}", product.getName());
            String title = "My Car Report";
            String name = product.getName();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            model.addAttribute("title", title);
            model.addAttribute("name", name);
            model.addAttribute("dateTime", formattedDateTime);

            return "report";
//        } else {
//            logger.warn("Product not found or invalid type");
//            return "redirect:/mainscreen";
//        }


    }
}
