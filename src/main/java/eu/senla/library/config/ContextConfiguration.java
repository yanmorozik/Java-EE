package eu.senla.library.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"eu.senla.library.controller", "eu.senla.library.service", "eu.senla.library.repository","eu.senla.library.aspect"})
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.datasource.username}")
    private String username;

    @Value(value = "${spring.datasource.password}")
    private String password;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(destroyMethod = "close")
    @SneakyThrows
    public Connection connection(){
        return DriverManager.getConnection(url,username,password);
    }
}
