package br.com.api.models.entities;

import br.com.api.core.enums.SecurityLevel;
import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements IGenericEntity, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "security_level", nullable = false)
    private SecurityLevel securityLevel;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
