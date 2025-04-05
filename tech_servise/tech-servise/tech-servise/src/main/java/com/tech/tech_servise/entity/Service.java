package com.tech.tech_servise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service")
public class Service {

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

    @Column(name = "create_srv")
    @CreationTimestamp  //  для автоматического создания времени
    private LocalDateTime createSrv;

    @Column(name = "update_srv")
    @UpdateTimestamp
    private LocalDateTime updateSrv;

    @ManyToOne
    public ServiceType type;

}
