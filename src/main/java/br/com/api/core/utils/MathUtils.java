package br.com.api.core.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

@UtilityClass
public class MathUtils {

    public static BigDecimal divideBigDecimalByInteger(BigDecimal value, Integer divisor) {
        return value.divide(BigDecimal.valueOf(divisor.longValue()), 2, HALF_EVEN);
    }

}
