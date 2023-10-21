package br.com.api.service;

import br.com.api.core.enums.NotificationType;
import br.com.api.models.vo.GenericNotification;
import br.com.api.repository.NotificationRepository;
import br.com.api.service.generics.GenericNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static br.com.api.core.enums.NotificationStatus.ERROR;
import static br.com.api.core.enums.NotificationStatus.SEND;
import static br.com.api.core.enums.NotificationType.EMAIL;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    @Async
    
    public void sendNotification(GenericNotification genericNotification) {
        try {
            genericNotification.buildPreNotification();

            var notificationService = buildNotificationFactory(genericNotification.getNotificationType());
            notificationService.sendSimpleNotification(genericNotification);

            genericNotification.getNotification().setNotificationStatus(SEND);
            genericNotification.getNotification().setSendingAt(now());
        } catch (Exception e) {
            genericNotification.getNotification().setNotificationStatus(ERROR);
        } finally {
            this.notificationRepository.saveAndFlush(genericNotification.getNotification());
        }
    }

    public GenericNotificationService buildNotificationFactory(NotificationType type) {
        if (EMAIL.equals(type)) {
            return this.emailService;
        } else {
            return null;
        }
    }

}
