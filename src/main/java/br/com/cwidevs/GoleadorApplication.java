package br.com.cwidevs;

import br.com.cwidevs.config.DefaultProfileUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GoleadorApplication {

    private static final Logger log = LoggerFactory.getLogger(GoleadorApplication.class);

    private final Environment env;

    public GoleadorApplication(Environment env) {
        this.env = env;
    }
    
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(GoleadorApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());
    }

}
