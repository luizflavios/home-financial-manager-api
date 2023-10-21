package br.com.api.models.entities;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "installments")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Installment implements IGenericEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = EAGER, optional = false)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Enumerated(ORDINAL)
    @Column(nullable = false, name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(nullable = false, name = "due_date")
    private LocalDate dueDate;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
}
