package br.com.cwidevs;

import br.com.cwidevs.config.DefaultProfileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoleadorApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GoleadorApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        app.run(args);
    }

}
