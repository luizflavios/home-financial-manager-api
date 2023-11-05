package br.com.api.models.entities;

import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "incomes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Income implements IGenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = EAGER, cascade = DETACH)
    @JoinColumn(name = "income_category", nullable = false)
    private IncomeCategory incomeCategory;

    @ManyToOne(optional = false, fetch = EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
