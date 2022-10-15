package org.personales.msvcproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsvcProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcProductosApplication.class, args);
    }

}
