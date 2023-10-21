package br.com.api.core.enums;

public enum PaymentWay {
    MONEY,
    DEBIT_CARD,
    CREDIT_CARD,
    FINANCING,
    AUTOMATIC_DEBIT;

    public static Boolean paymentMayHaveInstallments(PaymentWay paymentWay) {
        return paymentWay.equals(CREDIT_CARD) || paymentWay.equals(FINANCING) || paymentWay.equals(AUTOMATIC_DEBIT);
    }

}
