package br.com.api.service;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.core.generics.IJpaSpecificationRepository;
import br.com.api.core.generics.impl.GenericService;
import br.com.api.models.dto.installments.InstallmentByTransactionResponseDTO;
import br.com.api.models.dto.installments.InstallmentRequestDTO;
import br.com.api.models.dto.installments.InstallmentResponseDTO;
import br.com.api.models.entities.Installment;
import br.com.api.models.mapper.InstallmentMapper;
import br.com.api.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static br.com.api.core.enums.PaymentStatus.OPEN;
import static br.com.api.core.enums.PaymentStatus.PAID;
import static java.math.RoundingMode.HALF_EVEN;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toCollection;

@Service
public class InstallmentService extends GenericService<InstallmentRequestDTO, InstallmentResponseDTO, Installment> {


    private final InstallmentMapper installmentMapper;

    protected InstallmentService(IJpaSpecificationRepository<Installment, Long> genericRepository,
                                 @Qualifier("installmentMapperImpl") IGenericMapper<InstallmentRequestDTO, InstallmentResponseDTO, Installment> genericMapper,
                                 InstallmentMapper installmentMapper) {
        super(genericRepository, genericMapper);
        this.installmentMapper = installmentMapper;
    }

    @Override
    protected void overridePropertyOnCreateForEntity(Installment entity) {
        super.overridePropertyOnCreateForEntity(entity);
        entity.setPaymentStatus(OPEN);
    }

    public Set<Installment> buildInstallments(BigDecimal amount, Integer installmentsQuantity, LocalDate startDate) {
        Set<Installment> installments = new HashSet<>();
        var installmentValue = amount.divide(BigDecimal.valueOf(installmentsQuantity), 2, HALF_EVEN);

        for (int i = 1; i <= installmentsQuantity; i++) {
            installments.add(Installment.builder()
                    .amount(installmentValue)
                    .paymentStatus(OPEN)
                    .dueDate(i == 1 ? startDate : startDate.plusMonths((long) i - 1))
                    .build());
        }

        return installments.stream().sorted(Comparator.comparing(Installment::getDueDate)).collect(toCollection(LinkedHashSet::new));
    }

    public void payInstallment(Installment installment) {
        installment.setPaymentStatus(PAID);
        installment.setPaidAt(now());
        this.genericRepository.save(installment);
    }

    public List<InstallmentByTransactionResponseDTO> getInstallmentsByTransaction(Long transactionId) {
        return ((InstallmentRepository) this.genericRepository)
                .findByTransactionId(transactionId)
                .stream()
                .map(installmentMapper::toInstallmentByTransactionResponseDTO)
                .toList();
    }
}
