package br.com.api.models.entities;

import br.com.api.core.enums.NotificationStatus;
import br.com.api.core.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.EAGER;


@Entity
@Table(name = "notifications")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = EAGER)
    @JoinColumn(nullable = false, name = "user_id", updatable = false)
    private User user;

    @Enumerated(ORDINAL)
    @Column(nullable = false, name = "notification_type", updatable = false)
    private NotificationType notificationType;

    @Enumerated(ORDINAL)
    @Column(nullable = false, name = "notification_status")
    private NotificationStatus notificationStatus;

    @Column(name = "sending_at")
    private LocalDateTime sendingAt;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;
}
