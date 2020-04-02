package tver.wa.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class ClientApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApiApplication.class, args);
    }
}
