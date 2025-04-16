package com.tech.tech_servise.constants;

import lombok.Getter;

@Getter
public enum TypeClient {
    NEW("Новый"), OLD("Постоянный");
    private final String typeClientRus;


    TypeClient(String typeClientRus) {
        this.typeClientRus = typeClientRus;
    }
}
