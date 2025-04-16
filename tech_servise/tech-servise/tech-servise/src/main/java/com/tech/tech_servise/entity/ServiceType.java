package com.tech.tech_servise.entity;


import com.tech.tech_servise.constants.TypeService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "service_type")
public class ServiceType {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_lat", nullable = false)
    private TypeService nameLat;

    @Column(name = "name_rus", nullable = false)
    private String nameRus;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;
}
