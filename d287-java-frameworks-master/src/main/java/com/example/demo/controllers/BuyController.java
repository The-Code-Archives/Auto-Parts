package com.example.demo.controllers;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class BuyController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/buyProduct")
    public String buyProductSuccess(@RequestParam("productID") long pID, Model model) {
        Optional<Product> product = productRepository.findById(pID);

        if (product.isPresent()) {
            Product getProduct = product.get();

            if (getProduct.getInv() > 0) {
                getProduct.setInv(getProduct.getInv() - 1);
                productRepository.save(getProduct);
                String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
                String confirmationNumber = uuid.substring(0, 20).toUpperCase(); // Generate a random alphanumeric string
                model.addAttribute("confirmationNumber", confirmationNumber);
                return "purchaseSuccess";
            } else {
                return "purchaseFailure";
            }

        } else {
            return "purchaseFailure";
        }
    }
}
