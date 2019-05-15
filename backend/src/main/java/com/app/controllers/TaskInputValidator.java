package com.app.controllers;

import com.app.exceptions.BadRequestException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Validates json params
 */
public class TaskInputValidator {

    /**
     * Throws {@link BadRequestException} if the input is not valid
     *
     * @param body
     */
    public static void validateNumber(JsonNode body) {
        JsonNode numberJN = body.get("number");
        if (numberJN == null) {
            throw new BadRequestException("A number is required");
        }

        int number;
        try {
            number = Integer.parseInt(numberJN.asText());
        } catch (NumberFormatException e) {
            throw new BadRequestException("A number (less than " + Integer.MAX_VALUE + ") is required");
        }

        if (number <= 0 ) {
            throw new BadRequestException("A number greater than zero is required");
        }
    }

    /**
     * Throws {@link BadRequestException} if the input is not valid
     *
     * @param body
     */
    public static void validateId(JsonNode body) {
        JsonNode id = body.get("id");
        if (id == null) {
            throw new BadRequestException("An id is required");
        }
        if (id.asText().length() < 1) {
            throw new BadRequestException("The id cannot be empty");
        }
    }
}
