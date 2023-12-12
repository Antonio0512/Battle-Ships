package com.battleship.battleship.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.battleship.battleship.models.dtos.UserLoginDTO;
import com.battleship.battleship.models.dtos.UserRegistrationDTO;
import com.battleship.battleship.services.AuthService;

import jakarta.validation.Valid;

@Controller
public class AuthContoller {
    private AuthService authService;

    public AuthContoller(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute("loginDTO")
    public UserLoginDTO initLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(userRegistrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login (@Valid UserLoginDTO userLoginDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

}
