package meng.learn.eurekaadminclientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaAdminClientOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAdminClientOneApplication.class, args);
    }

}

