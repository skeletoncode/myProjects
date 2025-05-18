package com.tech.tech_servise.controller;


import com.tech.tech_servise.entity.ApplicationUser;
import com.tech.tech_servise.entity.UserRole;
import com.tech.tech_servise.exceptions.AccountException;
import com.tech.tech_servise.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/registration")
    public String registration(ApplicationUser applicationUser) {
        return "account/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping("/registration")
    public String createAccount(ApplicationUser user, Model model) {
        try {
            accountService.registration(user);
            return "redirect:/account/login";
        } catch (AccountException e) {
            model.addAttribute("error", e.getMessage());
            return "account/registration";

        }

    }
    @PostMapping("/changeProfile")
    public String changeProfile(ApplicationUser user, Model model, String newLogin, String newPassword, UserRole role) {

        return "";
    }


}
