package com.qph.attendancerecord.application.port.in.model;

import com.qph.attendancerecord.common.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class TuesdayOnTest {
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"12", "test", "12.35"})
    void test_fromString_given_not_boolean_value_then_throw_ValidationException(String value) {
        // GIVEN
        // WHEN
        assertThatThrownBy(() -> TuesdayOn.fromString(value))
                .isExactlyInstanceOf(ValidationException.class)
                .hasMessage("TuesdayOn must be true or false but get " + value);
        // THEN
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    void test_fromString_given_boolean_value_then_return_object(String value) throws ValidationException {
        // GIVEN
        // WHEN
        TuesdayOn result = TuesdayOn.fromString(value);
        // THEN
        assertThat(result.getValue()).isEqualTo(Boolean.valueOf(value));
    }
}