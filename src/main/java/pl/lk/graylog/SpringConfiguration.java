package pl.lk.graylog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringConfiguration {

    @Bean
    public String graylogGelfURL() {
        return "http://localhost:12201/gelf";
    }

}
