package iuh.fit.boshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoShopApplication.class, args);
    }

}
