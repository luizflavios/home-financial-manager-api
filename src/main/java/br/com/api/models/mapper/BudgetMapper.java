package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.utils.MathUtils;
import br.com.api.models.dto.budget.BudgetRequestDTO;
import br.com.api.models.dto.budget.BudgetResponseDTO;
import br.com.api.models.entities.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", imports = {TransactionMapper.class, Collectors.class, Objects.class, MathUtils.class})
public interface BudgetMapper extends IGenericMapper<BudgetRequestDTO, BudgetResponseDTO, Budget> {

    @Override
    @Mapping(target = "transactions",
            expression = "java(Objects.isNull(entity.getTransactions()) ? null : entity.getTransactions().stream().map(t -> TransactionMapper.INSTANCE.toDTO(t)).collect(Collectors.toSet()))")
    @Mapping(target = "balanceDue", expression = "java(MathUtils.budgetBalanceDueCalculator(entity))")
    BudgetResponseDTO toDTO(Budget entity);
}
