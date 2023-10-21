package br.com.api.service;

import br.com.api.models.vo.GenericNotification;
import br.com.api.service.generics.GenericNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService implements GenericNotificationService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendSimpleNotification(GenericNotification notification) {
        log.info("sending simple mail message");
        var message = new SimpleMailMessage();
        message.setTo(notification.getUser().getEmail());
        message.setFrom("noreply@homefinancialmanager.com");
        message.setSubject(notification.getSubject());
        message.setText(notification.getContent());
        javaMailSender.send(message);
        log.info("message successful send");
    }
}
