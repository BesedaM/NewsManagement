package com.epam.lab.beseda.service.validator;

import com.epam.lab.beseda.dto.BaseDTO;
import com.epam.lab.beseda.exception.validation.ValidationException;

@FunctionalInterface
public interface Validatable<E extends BaseDTO> {

    void validate(E entity) throws ValidationException;
}
