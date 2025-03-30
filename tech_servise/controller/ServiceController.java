package com.tech.tech_servise.controller;

import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RequestMapping("/api/v1/service")
@RestController
public class ServiceController {

    @PostMapping
    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO) {
        return 0L;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO){

    }


    @GetMapping()
    public ServiceResponseDTO serviceResponseDTO(@Positive @RequestParam int id) {

        return null;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ServiceRequestDTO> getAll(){
        return null;
    }





}
