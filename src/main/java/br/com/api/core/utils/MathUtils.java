package br.com.api.core.utils;

import br.com.api.models.entities.Budget;
import br.com.api.models.entities.Installment;
import br.com.api.models.entities.Transaction;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Set;

import static br.com.api.core.enums.PaymentStatus.PAID;
import static br.com.api.core.enums.PaymentWay.paymentMayHaveInstallments;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@UtilityClass
public class MathUtils {

    public static final Integer ZERO = 0;

    public static BigDecimal transactionBalanceDueCalculator(Transaction transaction) {
        if (TRUE.equals(paymentMayHaveInstallments(transaction.getPaymentWay()))
                && nonNull(transaction.getInstallments())
                && FALSE.equals(transaction.getInstallments().isEmpty())) {

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

    public static BigDecimal budgetBalanceDueCalculator(Budget budget) {
        return isNull(budget.getTransactions()) ? null : budget.getTransactions().stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
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
