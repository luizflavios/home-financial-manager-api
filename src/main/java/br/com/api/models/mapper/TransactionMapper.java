package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.utils.MathUtils;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Mapper(componentModel = "spring", imports = {Objects.class, MathUtils.class})
public interface TransactionMapper extends IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> {

    @Override
    @Mapping(target = "quantityOfInstallments", expression = "java(Objects.nonNull(entity.getInstallments()) ? entity.getInstallments().size() : null)")
    @Mapping(target = "paidInstallments", expression = "java(MathUtils.paidInstallmentsQuantity(entity.getInstallments()))")
    @Mapping(target = "balanceDue", expression = "java(MathUtils.balanceDueCalculator(entity))")
    TransactionResponseDTO toDTO(Transaction entity);
}
