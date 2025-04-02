package com.tech.tech_servise.service;

import com.tech.tech_servise.constants.TypeService;
import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServ {
    public Long createService(ServiceRequestDTO serviceRequestDTO) {
        // TODO :: add to database
        return 0L;
    }
    public ServiceResponseDTO getServiceById(int id) {
        // TODO :: get from database by id
        return null;
    }
    public String changeServiceByName(String name, String newName) {
        // TODO :: update database
        return null;

    }
    public List<ServiceResponseDTO> getAllServices() {
        // TODO :: get allServices from database
        return null;
    }

    public List<ServiceResponseDTO> getServiceByType(TypeService type) {
        // TODO :: get Service by type from database
        return List.of();
    }


}
