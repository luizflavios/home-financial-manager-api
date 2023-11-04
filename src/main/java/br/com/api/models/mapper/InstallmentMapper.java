package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.installments.InstallmentByTransactionResponseDTO;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.entities.Installment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface InstallmentMapper extends IGenericMapper<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {
    InstallmentByTransactionResponseDTO toInstallmentByTransactionResponseDTO(Installment installment);
}
