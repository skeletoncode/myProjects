package com.tech.tech_servise.controller;

import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponceDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/v1/services")
@RestController
public class ServiceController {

    @PostMapping
    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO) {

        return 0L;
    }

    @GetMapping
    public ServiceResponceDTO getServiceById(@Positive @RequestParam int id) {

        return null;
    }

// этот метод не функционирует
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String changeServiceByName (@RequestParam String name, @RequestParam String newName) {

        return name;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ServiceRequestDTO> getAllServices() {
        return null;
    }

}
