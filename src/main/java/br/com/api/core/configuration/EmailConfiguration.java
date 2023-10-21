package br.com.api.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static br.com.api.core.constants.AppConstants.*;
import static java.lang.Boolean.TRUE;

@Configuration
public class EmailConfiguration {

    @Value("${api.mailer.host}")
    private String apiMailerHost;

    @Value("${api.mailer.port}")
    private Integer apiMailerPort;

    @Value("${api.mailer.tls}")
    private Boolean apiMailerTLS;

    @Value("${api.mailer.username}")
    private String apiMailerUsername;

    @Value("${api.mailer.password}")
    private String apiMailerPassword;

    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(apiMailerHost);
        mailSender.setPort(apiMailerPort);
        mailSender.setUsername(apiMailerUsername);
        mailSender.setPassword(apiMailerPassword);

        var props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, SMTP);
        props.put(MAIL_SMTP_AUTH, TRUE.toString());
        props.put(MAIL_SMTP_STARTTLS_ENABLE, apiMailerTLS.toString());
        return mailSender;
    }

}
