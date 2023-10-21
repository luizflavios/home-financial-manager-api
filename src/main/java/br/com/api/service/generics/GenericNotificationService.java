package br.com.api.service.generics;

import br.com.api.models.vo.GenericNotification;

public interface GenericNotificationService {
    void sendSimpleNotification(GenericNotification genericNotification);
}
