package com.tech.tech_servise.repository;

import com.tech.tech_servise.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
