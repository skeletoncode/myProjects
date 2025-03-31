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
//   не работает в POSTMAN
//    @PostMapping("{name}")
//    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO, @PathVariable("name") String name ) {
//
//        return 0L;
//    }
    @PostMapping
    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO) {

        return 0L;
    }

    @GetMapping
    public ServiceResponceDTO getServiceById(@Positive @RequestParam int id) {

        return null;
    }


//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String changeServiceByName (@RequestParam String name, @RequestParam String newName) {
//
//        return name;
//    }
    @PostMapping(value = "/update")
    public String changeServiceByName (@RequestParam String name, @RequestParam String newName) {

    return name;
    }

    @GetMapping(value = "/all")
    public List<ServiceRequestDTO> getAllServices() {
        return null;
    }
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<ServiceRequestDTO> getAllServices() {
//        return null;
//    }

}
