package br.com.api.core.generics;

import br.com.api.core.enums.FilterComparison;
import lombok.Builder;

import static java.util.Objects.nonNull;

@Builder
public record FilterCriteria(String property, FilterComparison operator, String value) {

    public Boolean isValid() {
        return nonNull(property) && nonNull(operator) && nonNull(value);
    }

}
