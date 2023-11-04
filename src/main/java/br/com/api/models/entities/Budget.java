package br.com.api.models.entities;

import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Budget implements IGenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @ToString.Exclude
    private Family family;

    @Column(nullable = false, name = "total_income")
    private BigDecimal totalIncome;

    @Column(nullable = false, name = "total_expense")
    private BigDecimal totalExpense;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "budget", cascade = ALL, fetch = LAZY)
    @ToString.Exclude
    private Set<Transaction> transactions;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;
}
