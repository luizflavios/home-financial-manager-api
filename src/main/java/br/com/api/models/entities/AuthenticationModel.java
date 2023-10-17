package br.com.api.models.entities;

import br.com.api.core.enums.AuthenticationModelStatus;
import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static br.com.api.core.enums.AuthenticationModelStatus.INACTIVE;
import static jakarta.persistence.EnumType.ORDINAL;

@Entity
@Table(name = "authentications_models")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationModel implements IGenericEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "authenticationModel")
    private User user;
    @Enumerated(ORDINAL)
    @Column(nullable = false)
    private AuthenticationModelStatus status;
    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(updatable = false, nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    public Boolean isActive() {
        return !this.status.equals(INACTIVE);
    }
}
