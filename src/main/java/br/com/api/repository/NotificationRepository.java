package br.com.api.repository;

import br.com.api.core.enums.NotificationStatus;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.models.entities.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface NotificationRepository extends IJpaSpecificationRepository<Notification, Long> {

    @Modifying
    @Query("update Notification n set n.notificationStatus = :status, n.sendingAt = :sendingAt where n.id = :id")
    void update(NotificationStatus status, LocalDateTime sendingAt, Long id);
}
