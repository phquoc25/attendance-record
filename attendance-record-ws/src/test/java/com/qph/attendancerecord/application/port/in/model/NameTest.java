package com.qph.attendancerecord.application.port.in.model;

import com.qph.attendancerecord.common.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"123", "test name", "12.3", "true", "false"})
    void test_FromString_given_valid_value_then_return_object(String value) throws ValidationException {
        // GIVEN
        // WHEN
        Name result = Name.fromString(value);
        // THEN
        assertThat(result.getValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void test_FromString_given_null_or_empty_value_then_throw_ValidationException(String value) throws ValidationException {
        // GIVEN
        // WHEN
        assertThatThrownBy(() -> Name.fromString(value))
                .isExactlyInstanceOf(ValidationException.class)
                .hasMessage("name must not be empty or null but get " + value);
        // THEN
    }
}