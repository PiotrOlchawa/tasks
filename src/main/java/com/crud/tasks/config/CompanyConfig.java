package com.crud.tasks.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "info.company")
public class CompanyConfig {

    /*@Value("${name}")*/
    private String name;

    private String goal;

    private String email;

    private String phone;
}