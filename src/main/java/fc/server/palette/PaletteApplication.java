package fc.server.palette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PaletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaletteApplication.class, args);
    }

}
