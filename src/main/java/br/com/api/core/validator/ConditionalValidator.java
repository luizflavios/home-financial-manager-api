package br.com.api.core.validator;

import br.com.api.core.annotations.Conditional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Slf4j
public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

    private String selected;
    private String[] required;
    private String message;
    private String[] values;

    @Override
    public void initialize(Conditional requiredIfChecked) {
        selected = requiredIfChecked.selected();
        required = requiredIfChecked.required();
        message = requiredIfChecked.message();
        values = requiredIfChecked.values();
    }

    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        boolean valid = true;
        var wrapper = new BeanWrapperImpl(objectToValidate);
        var actualValue = wrapper.getPropertyValue(selected);
        if (Arrays.stream(values).anyMatch(value -> value.equals(requireNonNull(actualValue).toString()))) {
            for (String propName : required) {
                Object requiredValue = wrapper.getPropertyValue(propName);
                valid = requiredValue != null && !isEmpty(requiredValue);
                log.info("value: {}", requiredValue);
                if (!valid) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(message).addPropertyNode(propName).addConstraintViolation();
                    break;
                }
            }
        }
        return valid;
    }
}