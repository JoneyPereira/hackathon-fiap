package com.fiap.hackathon.resources.exceptions;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StanderError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError (String fieldName, String message){

        errors.add(new FieldMessage(fieldName, message));
    }
}
