package com.tech.tech_servise.controller;

import com.tech.tech_servise.constants.TypeService;
import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import com.tech.tech_servise.exceptions.ServiceException;
import com.tech.tech_servise.service.ServiceServ;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/v1/services")
@RestController
public class ServiceController {
    // Model(логика программы) View(представление--json строка) Controller
    // status, header, body -- сообщение
    // ResponseEntity<?> может быть телео может быть без тел
    // ResponseEntity<void> тело всегда пустое
    // ResponseEntity<BODY_DATA_TYPE> тип тела ответа
    // BODY_DATA_TYPE



    private final ServiceServ serviceServ;


//   не работает в POSTMAN,
//    @PostMapping("{name}")
//    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO, @PathVariable("name") String name ) {
//
//        return 0L;
//    }
/*{
    "id": "1",
        "date_reservation": "2026-07-15 15:00",
        "date_create_reservation": "2025-01-15 15:00",
        "manger": "manager",
        "client": "client",
        "type_client": "NEW",
        "gender_client": "MALE",
        "services": [23, 78]

} */

    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO, HttpServletRequest request) {
        log.debug("POST request. Service {}", serviceRequestDTO);
        URI uri = URI.create("/api/v1/services" + serviceServ.createService(serviceRequestDTO));
        return ResponseEntity.created(uri).build(); // status 201
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ServiceResponseDTO> getServiceById(@Positive @RequestParam Long id) {
        log.info("GET request with Service ID {}", id);
        try {
            return new ResponseEntity<>(serviceServ.getServiceById(id), HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }



//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String changeServiceByName (@RequestParam String name, @RequestParam String newName) {
//
//        return name;
//    }
    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<String> changeServiceByName (@RequestParam String name, @RequestParam String newName) {
        log.info("POST change Service newName = {}", newName);
    return new ResponseEntity<>(serviceServ.changeServiceByName(name, newName), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<ServiceResponseDTO> getAllServices() {
        log.info("GET all services");
        return serviceServ.getAllServices();
    }

    @GetMapping(path = "/{type}", produces = "application/json")
    public List<ServiceResponseDTO> getServicesByType(@PathVariable TypeService type) {
        log.info("GET request with Service type {}", type);
        return serviceServ.getServiceByType(type);
    }
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<ServiceRequestDTO> getAllServices() {
//        return null;
//    }

}
