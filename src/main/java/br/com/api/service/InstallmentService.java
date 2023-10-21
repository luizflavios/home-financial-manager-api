package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.entities.Installment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static br.com.api.core.enums.PaymentStatus.OPEN;

@Service
public class InstallmentService extends GenericService<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {

    protected InstallmentService(IJpaSpecificationRepository<Installment, Long> genericRepository,
                                 @Qualifier("installmentMapperImpl") IGenericMapper<InstallmentRequestDTO, InstallmentResponseDTO, Installment> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Installment entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
    }
}
