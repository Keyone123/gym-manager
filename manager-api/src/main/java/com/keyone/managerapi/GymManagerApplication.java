package com.keyone.managerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Classe principal. Como as entidades e repositories vivem no modulo "core"
 * (pacote com.keyone.core), precisamos apontar explicitamente o EntityScan e o
 * EnableJpaRepositories para la, ja que o @SpringBootApplication por padrao so
 * escaneia o pacote onde ele mesmo esta (com.keyone.managerapi) e seus subpacotes.
 */
@SpringBootApplication(scanBasePackages = "com.keyone")
@EntityScan(basePackages = "com.keyone.core.models")
@EnableJpaRepositories(basePackages = "com.keyone.core.repositories")
@ConfigurationPropertiesScan(basePackages = "com.keyone.managerapi")
public class GymManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymManagerApplication.class, args);
    }
}
