package com.tech.tech_servise.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service")
@AllArgsConstructor
public class Servise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "serviceName")
    private String serviceName;


    @Column(name = "master")
    private String master;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "service_status")
    private Boolean serviceStatus;

    @Column(name = "create_srv")
    @CreationTimestamp  //  для автоматического создания времени
    private LocalDateTime createSrv;

    @Column(name = "update_srv")
    @UpdateTimestamp
    private LocalDateTime updateSrv;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

    @ManyToOne
    private ServiceType serviceType;

}
