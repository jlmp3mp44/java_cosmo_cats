package com.example.cosmocats.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CosmicWordCheckValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private static final String[] COSMIC_TERMS = {"star", "galaxy", "comet"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        for (String term : COSMIC_TERMS) {
            if (value.toLowerCase().contains(term)) {
                return true;
            }
        }
        return false;
    }
}

