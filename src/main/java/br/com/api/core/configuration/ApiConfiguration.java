package br.com.api.core.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApiConfiguration {

    @Value("${secret.key}")
    private String secretKey;
    @Value("${token.life}")
    private Long tokenLife;

}
