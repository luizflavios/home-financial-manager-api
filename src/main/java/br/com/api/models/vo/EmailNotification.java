package br.com.api.models.vo;

import br.com.api.core.enums.NotificationType;
import br.com.api.models.entities.User;


public class EmailNotification extends GenericNotification {
    public EmailNotification(User user, NotificationType notificationType, String content, String templateSource, String subject) {
        super(user, notificationType, content, templateSource, subject);
    }
}
