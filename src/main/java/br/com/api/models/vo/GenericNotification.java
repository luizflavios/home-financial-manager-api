package br.com.api.models.vo;

import br.com.api.core.enums.NotificationType;
import br.com.api.models.entities.Notification;
import br.com.api.models.entities.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class GenericNotification {

    private final User user;
    private final NotificationType notificationType;
    private final String content;
    private final String templateSource;
    private final String subject;
    private Notification notification;

    public void buildPreNotification() {
        notification = Notification.builder()
                .user(this.user)
                .notificationType(this.notificationType)
                .build();
    }

}
