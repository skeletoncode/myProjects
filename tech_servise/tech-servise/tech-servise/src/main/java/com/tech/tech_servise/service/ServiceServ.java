package com.tech.tech_servise.service;

import com.tech.tech_servise.constants.TypeService;
import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import com.tech.tech_servise.entity.ServiceType;
import com.tech.tech_servise.entity.Servise;
import com.tech.tech_servise.mapper.ServiceMapper;
import com.tech.tech_servise.repository.ServiceRepository;
import com.tech.tech_servise.repository.ServiceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServiceServ {
    private final ServiceRepository serviceRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceMapper serviceMapper;


    public Long createService(ServiceRequestDTO serviceRequestDTO) {
        ServiceType serviceType = serviceTypeRepository
                .findAllByNameLat(serviceRequestDTO.typeService())
                .get(); // TODO:: bad request exception

        Servise services = serviceMapper.mapToEntity(serviceRequestDTO);
        services.setServiceType(serviceType);

        serviceRepository.save(services); // TODO:: Creation exception
        // TODO :: add to database
        return services.getId();
    }
    public ServiceResponseDTO getServiceById(Long id) {
        // TODO :: get from database by id
        Servise servise  = serviceRepository.findById(id)
                .get();

        return serviceMapper.mapToDTO(servise);
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
        List<Servise> servises = serviceRepository
                .findAllByServiceTypeNameRusAndIsActiveTrue(type.toString());
        return servises.stream().map(serviceMapper::mapToDTO).toList();

        // TODO :: Not found Exception
    }


}
