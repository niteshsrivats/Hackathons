package beatmanagement.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Configuration
@EnableJpaRepositories(basePackages = "beatmanagement.central.repositories")
//@EnableRedisRepositories(basePackages = "beatmanagement.central.repositories.redis")
@SpringBootApplication(
        scanBasePackages = {
                "beatmanagement.central.controller",
                "beatmanagement.central.config",
                "beatmanagement.central.models",
                "beatmanagement.central.repositories",
                "beatmanagement.central.service"
        }
)
public class CentralApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralApplication.class, args);
    }
}
