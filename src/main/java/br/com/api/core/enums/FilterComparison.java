package br.com.api.core.enums;

import lombok.Getter;

@Getter
public enum FilterComparison {

    EQUALS("="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    NOT_EQUALS("!="),
    CONTAINS("~"),
    NOT_CONTAINS("!~"),
    BEGINS_WITH("^");

    private final String operarator;

    FilterComparison(String operarator) {
        this.operarator = operarator;
    }
}
