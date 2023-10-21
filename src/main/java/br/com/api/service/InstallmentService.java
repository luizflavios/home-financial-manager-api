package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.entities.Installment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static br.com.api.core.enums.PaymentStatus.OPEN;
import static br.com.api.core.utils.MathUtils.divideBigDecimalByInteger;

@Service
public class InstallmentService extends GenericService<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {

    protected InstallmentService(IJpaSpecificationRepository<Installment, Long> genericRepository,
                                 @Qualifier("installmentMapperImpl") IGenericMapper<InstallmentRequestDTO, InstallmentResponseDTO, Installment> genericMapper) {
        super(genericRepository, genericMapper);
    }

    public Set<InstallmentRequestDTO> buildRecursiveInstallments(TransactionRequestDTO request) {
        var installments = new HashSet<InstallmentRequestDTO>();
        var dueDate = request.getStartOfBilling();

        for (int i = 0; i <= request.getQuantityOfInstallments(); i++) {
            var installment = InstallmentRequestDTO.builder()
                    .paymentStatus(OPEN)
                    .amount(divideBigDecimalByInteger(request.getAmount(), request.getQuantityOfInstallments()))
                    .dueDate(dueDate)
                    .build();

            if (i != 0) {
                dueDate = dueDate.plusMonths(1);
            }

            installments.add(installment);
        }

        return installments;
    }
}
