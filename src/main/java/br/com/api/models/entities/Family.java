package br.com.api.models.entities;

import br.com.api.core.generics.IGenericEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "families")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Family implements IGenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = LAZY)
    @JoinTable(name = "family_users",
            joinColumns = @JoinColumn(name = "family_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "family", cascade = ALL, fetch = LAZY)
    @ToString.Exclude
    private Set<TransactionCategory> transactionCategories;


    @OneToMany(mappedBy = "family", cascade = ALL, fetch = LAZY)
    @ToString.Exclude
    private Set<IncomeCategory> incomeCategories;

}
