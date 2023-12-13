package com.battleship.battleship.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.battleship.battleship.models.dtos.CreateShipDTO;
import com.battleship.battleship.services.ShipService;
import com.battleship.battleship.sessions.LoggedUser;

import jakarta.validation.Valid;

@Controller
public class ShipController {
    private final ShipService shipService;
    private final LoggedUser loggedUser;

    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("shipDTO")
    public CreateShipDTO initShipDTO() {
        return new CreateShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {
        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        return "ship-add";
    }


    @PostMapping("/ships/add")
    public String addShip(@Valid CreateShipDTO shipDTO, 
                            BindingResult bindingResult, 
                            RedirectAttributes redirectAttributes
    ) {
        
        if (bindingResult.hasErrors() || !this.shipService.addShip(shipDTO, loggedUser)) {
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDTO", bindingResult);

            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
