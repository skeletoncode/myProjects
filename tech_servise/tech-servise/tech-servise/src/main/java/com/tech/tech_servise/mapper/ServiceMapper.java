package com.tech.tech_servise.mapper;

import com.tech.tech_servise.dto.ServiceRequestDTO;
import com.tech.tech_servise.dto.ServiceResponseDTO;
import com.tech.tech_servise.entity.Servise;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceMapper {
    public Servise mapToEntity(ServiceRequestDTO serviceRequestDTO) {
        return com.tech.tech_servise.entity.Servise.builder()
                .serviceName(serviceRequestDTO.serviceName())
                .master(serviceRequestDTO.master())
                .price(serviceRequestDTO.price())
                .description(serviceRequestDTO.description())
                .isActive(serviceRequestDTO.isActive())
                .build();
    }

    public ServiceResponseDTO mapToDTO(Servise service) {
        return new ServiceResponseDTO(
                service.getServiceName(),
                service.getMaster(),
                service.getPrice(),
                service.getDescription(),
                service.getId(),
                service.getCreateSrv()
        );
   }
}
