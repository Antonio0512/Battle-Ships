package com.battleship.battleship.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.battleship.battleship.models.dtos.ShipDTO;
import com.battleship.battleship.models.dtos.StartBattleDTO;
import com.battleship.battleship.services.ShipService;
import com.battleship.battleship.sessions.LoggedUser;

import java.util.List;

@Controller
public class HomeController {
    private final ShipService shipService;
    private final LoggedUser loggedUser;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {
        return new StartBattleDTO();
    }
    
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        if (loggedUser.getId() > 0) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        if (loggedUser.getId() == 0) {
            return "redirect:/";
        }

        long loggedUserId = this.loggedUser.getId();

        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedAllShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedAllShips", sortedAllShips);

        return "home";
    }
}
