package com.battleship.battleship.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.battleship.battleship.models.dtos.StartBattleDTO;
import com.battleship.battleship.services.BattleService;
import com.battleship.battleship.sessions.LoggedUser;

import jakarta.validation.Valid;

@Controller
public class BattleController {
    private final BattleService battleService;
    private final LoggedUser loggedUser;

    public BattleController(BattleService battleService, LoggedUser loggedUser) {
        this.battleService = battleService;
        this.loggedUser = loggedUser;
    }
    
    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO, 
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes
    ) {
        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDTO", bindingResult);
            
            return "redirect:/home";
        }

        this.battleService.attack(startBattleDTO);;

        return "redirect:/home";
    }
}
