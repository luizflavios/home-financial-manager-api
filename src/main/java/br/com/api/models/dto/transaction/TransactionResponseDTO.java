package br.com.api.models.dto.transaction;

import br.com.api.core.enums.PaymentStatus;
import br.com.api.core.enums.PaymentWay;
import br.com.api.core.generics.IGenericResponseDTO;
import br.com.api.models.dto.transactioncategory.TransactionCategoryResponseDTO;
import br.com.api.models.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDTO implements IGenericResponseDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private BigDecimal balanceDue;
    private PaymentStatus paymentStatus;
    private PaymentWay paymentWay;
    private LocalDate dueDate;
    private UserResponseDTO user;
    private TransactionCategoryResponseDTO transactionCategory;
    private Integer quantityOfInstallments;
    private Integer paidInstallments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
