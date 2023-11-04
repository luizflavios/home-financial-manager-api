package br.com.api.core.utils;

import br.com.api.models.entities.Installment;
import br.com.api.models.entities.Transaction;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Set;

import static br.com.api.core.enums.PaymentStatus.PAID;
import static br.com.api.core.enums.PaymentWay.paymentMayHaveInstallments;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@UtilityClass
public class MathUtils {

    public static final Integer ZERO = 0;

    public static BigDecimal balanceDueCalculator(Transaction transaction) {
        if (TRUE.equals(paymentMayHaveInstallments(transaction.getPaymentWay()))) {
            var amount = transaction.getAmount();

            var paidAmount = transaction.getInstallments()
                    .stream()
                    .filter(installment -> PAID.equals(installment.getPaymentStatus()))
                    .map(Installment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return amount.subtract(paidAmount);
        }
        return null;
    }

    public static Integer paidInstallmentsQuantity(Set<Installment> installments) {
        if (isNull(installments)) {
            return null;
        }

        return installments
                .stream()
                .filter(installment -> PAID.equals(installment.getPaymentStatus()))
                .toArray().length;
    }

}
