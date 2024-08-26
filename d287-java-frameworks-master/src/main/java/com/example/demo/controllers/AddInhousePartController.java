package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import com.example.demo.validators.InventoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController{
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel){
        InhousePart inhousepart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);
        InventoryValidator inventoryValidation = part.invValidation();
//        if(!part.invCheck()) {
//            theBindingResult.rejectValue("inv", "invalid.inventory",
//                    "Inventory value must be between minInv and maxInv.");
//            return "InhousePartForm";
//        }

        if(inventoryValidation != InventoryValidator.VALID) {
            String errorMessage;
            if(inventoryValidation == InventoryValidator.BELOW_MINIMUM) {
                errorMessage = "Inventory must be greater than or equal to minimum";
            } else {
                errorMessage = "Inventory must be less than or equal to maximum";
            }
            theBindingResult.rejectValue("inv", "invalid inventory", errorMessage);
            return "InhousePartForm";
        }

        if(theBindingResult.hasErrors()){
            return "InhousePartForm";
        } //else if (!part.invCheck()) {
//            theBindingResult.rejectValue("inv", "invalid.inventory",
//                    "Inventory value must be between minimum and maximum value.");
//            return "InhousePartForm";
       /* }*/
        else{

//            if(!part.invCheck()) {
//                theBindingResult.rejectValue("inv", "invalid.inventory",
//                        "Inventory value must be between minInv and maxInv.");
//                return "InhousePartForm";
//            }

        InhousePartService repo=context.getBean(InhousePartServiceImpl.class);
        InhousePart ip=repo.findById((int)part.getId());
        if(ip!=null)part.setProducts(ip.getProducts());
            repo.save(part);

        return "confirmationaddpart"; }
    }




}
