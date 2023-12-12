package com.battleship.battleship.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.battleship.battleship.models.dtos.ShipDTO;

import jakarta.validation.Valid;

@Controller
public class ShipController {
    @ModelAttribute("shipDTO")
    public ShipDTO initShipDTO() {
        return new ShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {
        return "ship-add";
    }


    @PostMapping("/ships/add")
    public String addShip(@Valid ShipDTO shipDTO, 
                            BindingResult bindingResult, 
                            RedirectAttributes redirectAttributes
    ) {
    
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDTO", bindingResult);

            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
