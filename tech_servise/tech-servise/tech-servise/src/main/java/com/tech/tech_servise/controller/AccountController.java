package com.tech.tech_servise.controller;


import com.tech.tech_servise.dto.ReservationResponseDTO;
import com.tech.tech_servise.entity.ApplicationUser;
import com.tech.tech_servise.entity.UserRole;
import com.tech.tech_servise.exceptions.AccountException;
import com.tech.tech_servise.repository.ReservationRepository;
import com.tech.tech_servise.service.AccountService;
import com.tech.tech_servise.service.ServiceReservation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ServiceReservation serviceReservation;


    public AccountController(AccountService accountService, ServiceReservation serviceReservation) {
        this.accountService = accountService;
        this.serviceReservation = serviceReservation;

    }

    @GetMapping(path = "/registration", produces = "application/json")
    public String registration(ApplicationUser applicationUser) {
        return "account/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping(path = "/registration", produces = "application/json")
    public String createAccount(ApplicationUser user, Model model) {
        try {
            accountService.registration(user);
            return "redirect:/account/login";
        } catch (AccountException e) {
            model.addAttribute("error", e.getMessage());
            return "account/registration";

        }

    }
    @PostMapping(path = "/changeProfile", produces = "application/json")
    public void changeProfile(ApplicationUser user, Model model, String newLogin, String newPassword, UserRole role) throws AccountException {


        accountService.changeProfile(user,newLogin,newPassword,role);

    }

    @PostMapping(path = "/changeUserReservation", produces = "application/json")
    public List<ReservationResponseDTO> changeReservation(ApplicationUser user, Long userId , Model model) {

        if(user.getUserRole().getRoleType() == UserRole.RoleType.ROLE_ADMIN) {
            return serviceReservation.getUserReservation(userId);
        }
        if(user.getUserRole().getRoleType() == UserRole.RoleType.ROLE_USER) {
            return serviceReservation.getUserReservation(userId);
        }
        if(user.getUserRole().getRoleType() == UserRole.RoleType.ROLE_OPERATOR) {
            return serviceReservation.getUserReservation(userId);
        }


        return List.of();
    }

    @PostMapping("/getUserReservations")
    public List<ReservationResponseDTO> getUserReservation(Long userId, Model model) {



        return serviceReservation.getUserReservation(userId);
    }

    @PostMapping("/discount")
    public double getDiscount(Long userId, Model model, int discount) {

        return accountService.getDiscount(userId, discount);
    }


}
