package br.com.api.models.dto.budget;

import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.family.FamilyResponseDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class BudgetResponseDTO implements IGenericResponseDTO {
    private Long id;
    private FamilyResponseDTO family;
    private BigDecimal totalIncome;
    private BigDecimal balanceDue;
    private BigDecimal totalExpense;
    private Boolean expenseAlert;
    private Boolean active;
    private Set<TransactionResponseDTO> transactions;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
}
