package com.app.controllers;

import com.app.exceptions.BadRequestException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static com.app.controllers.TaskInputValidator.validateId;
import static com.app.controllers.TaskInputValidator.validateNumber;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskInputValidatorTest {

    private static final String NUMBER_FIELD_NAME = "number";
    private static final String ID_FIELD_NAME = "id";
    private static final String WRONG_FIELD_NAME = "wrong";
    private static final String VALID_STRING_NUMBER = "654";
    private static final String NEGATIVE_STRING_NUMBER = "-654";
    private static final String TOO_BIG_STRING_NUMBER = "9999999999999999";
    private static final String EMPTY_STRING = "";
    private static final String NaN_STRING = "Hello world!";
    private static final String ZERO_STRING = "0";
    private static final String VALID_ID = "I'm a valid id";

    @Test
    void testValidateNumberWithValidNumber() {
        ObjectNode node = createObjectNode(NUMBER_FIELD_NAME, VALID_STRING_NUMBER);
        validateNumber(node);
    }

    @Test
    void testValidateNumberWithNegativeNumber() {
        assertValidateNumberThrowsBadRequestException(NUMBER_FIELD_NAME, NEGATIVE_STRING_NUMBER);
    }

    @Test
    void testValidateNumberWithTooBigNumber() {
        assertValidateNumberThrowsBadRequestException(NUMBER_FIELD_NAME, TOO_BIG_STRING_NUMBER);
    }

    @Test
    void testValidateNumberWithEmptyString() {
        assertValidateNumberThrowsBadRequestException(NUMBER_FIELD_NAME, EMPTY_STRING);
    }

    @Test
    void testValidateNumberWithNaNString() {
        assertValidateNumberThrowsBadRequestException(NUMBER_FIELD_NAME, NaN_STRING);
    }

    @Test
    void testValidateNumberWithZero() {
        assertValidateNumberThrowsBadRequestException(NUMBER_FIELD_NAME, ZERO_STRING);
    }

    @Test
    void testValidateNumberWithWrongFieldName() {
        assertValidateNumberThrowsBadRequestException(WRONG_FIELD_NAME, ZERO_STRING);
    }

    @Test
    void testValidateIdWithValidId() {
        ObjectNode node = createObjectNode(ID_FIELD_NAME, VALID_ID);
        validateId(node);
    }

    @Test
    void testValidateIdWithEmptyString() {
        assertValidateIdThrowsBadRequestException(ID_FIELD_NAME, EMPTY_STRING);
    }

    @Test
    void testValidateIdWithWrongFieldName() {
        assertValidateNumberThrowsBadRequestException(WRONG_FIELD_NAME, VALID_ID);
    }

    private void assertValidateNumberThrowsBadRequestException(String fieldName, String input) {
        ObjectNode node = createObjectNode(fieldName, input);
        assertThrows(BadRequestException.class, () -> validateNumber(node));
    }

    private void assertValidateIdThrowsBadRequestException(String fieldName, String input) {
        ObjectNode node = createObjectNode(fieldName, input);
        assertThrows(BadRequestException.class, () -> validateId(node));
    }

    private ObjectNode createObjectNode(String fieldName, String input) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put(fieldName, input);
        return node;
    }
}