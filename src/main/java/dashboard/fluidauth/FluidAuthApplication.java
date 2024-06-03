package dashboard.fluidauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dashboard.fluidauth"})
@ComponentScan(basePackages = {"dashboard.security"})
@ComponentScan(basePackages = {"dashboard.except"})
public class FluidAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluidAuthApplication.class, args);
    }

}
