package com.tech.tech_servise.constants;

import lombok.Getter;

@Getter
public enum TypeService {
    ENGINE("Двигатель"), CHASSIS("Подвеска"), WHEEL("Колеса"), BODY("Кузов"), ELECTRICIAN("Электрика");

    private final String nameRus;

    TypeService( String nameRus) {
        this.nameRus = nameRus;
    }
}
