package br.com.api.models.entities;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.enums.PaymentWay;
import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements IGenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false, fetch = EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(ORDINAL)
    @Column(nullable = false, name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(ORDINAL)
    @Column(nullable = false, name = "payment_way")
    private PaymentWay paymentWay;

    @Column(nullable = false, name = "due_date")
    private LocalDate dueDate;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "transaction", cascade = ALL, fetch = LAZY)
    @ToString.Exclude
    private Set<Installment> installments;
}
