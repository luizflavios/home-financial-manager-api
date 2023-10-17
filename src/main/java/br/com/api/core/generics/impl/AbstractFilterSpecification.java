package br.com.api.core.generics.impl;

import br.com.api.core.generics.FilterCriteria;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("java:S119")
public abstract class AbstractFilterSpecification<E> implements Specification<E> {

    protected final transient List<FilterCriteria> filters;

    protected AbstractFilterSpecification(List<FilterCriteria> filters) {
        this.filters = filters;
    }

    protected Predicate buildFilterPredicate(Path<E> root, CriteriaBuilder builder) {
        if (filters == null || filters.isEmpty()) {
            return null;
        }

        return builder.and(filters.stream()
                .map(filter -> buildFilterItemPredicate(filter, root, builder))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }

    protected Predicate[] stripNulls(Predicate... predicates) {
        return Arrays.stream(predicates).filter(Objects::nonNull).toArray(Predicate[]::new);
    }

    @SuppressWarnings("unused")
    protected Object convertToPredicateValue(String property, String value) {
        return value;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Predicate buildFilterItemPredicate(FilterCriteria criteria, Path<E> root, CriteriaBuilder builder) {

        if (criteria == null || !criteria.isValid() || StringUtils.isBlank(criteria.value())) {
            return null;
        }

        Path property = root.get(criteria.property());
        Object value = convertToPredicateValue(criteria.property(), criteria.value());

        return switch (criteria.operator()) {
            case EQUALS -> builder.equal(property, value);
            case LESS_THAN -> builder.lessThan(property, value.toString());
            case GREATER_THAN -> builder.greaterThan(property, value.toString());
            case NOT_EQUALS -> builder.notEqual(property, value);
            case LESS_THAN_OR_EQUAL -> builder.lessThanOrEqualTo(property, value.toString());
            case GREATER_THAN_OR_EQUAL -> builder.greaterThanOrEqualTo(property, value.toString());
            case CONTAINS -> builder.like(property, "%" + value + "%");
            case NOT_CONTAINS -> builder.notLike(property, "%" + value + "%");
            case BEGINS_WITH -> builder.like(property, value + "%");
        };
    }

}
