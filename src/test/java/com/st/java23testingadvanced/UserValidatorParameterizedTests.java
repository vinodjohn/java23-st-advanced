package com.st.java23testingadvanced;

import com.st.java23testingadvanced.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Parameterized tests using JUnit
 *
 * @author Vinod John
 * @Date 02.05.2023
 */
public class UserValidatorParameterizedTests {
    @ParameterizedTest
    @ValueSource(strings = {"Tony12345", "Mark28393", "jack113123sfsifudb", "239764ailwajshdv37"})
    public void givenUserPassword_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @MethodSource("getPasswords")
    public void givenUserPassword_whenEncodePasswordCalled_shouldReturnEncodedPasswords(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ArgumentsSource(Parameters.class)
    public void givenUserPasswordFromProvider_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvSource({"Vinod123456, Vinod#sda_java#123456", "John123456, John1#sda_java#23456"})
    public void givenUserPasswordFromCsv_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataSource.csv")
    public void givenUserPasswordFromCsvFile_whenEncodedPasswordCalled_shouldReturnEncodedPassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tony12345", "Mark28393", "jack113123sfsifudb", "239764ailwajshdv37"})
    public void givenUserPasswordConverted_whenEncodePasswordCalled_shouldReturnEncodedPassword(@ConvertWith(PasswordConverter.class) String password) {
        String expectedValue = password.substring(0, password.length() / 2) + "#sda_java#" + password.substring(password.length() / 2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    //For Method Source
    static Stream<Arguments> getPasswords() {
        return Stream.of(Arguments.of("12835smjdbf"), Arguments.of("aksdubiua238sf"));
    }

    //For Argument Source
    static class Parameters implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(Arguments.of("12835smjdbf"), Arguments.of("aksdubiua238sf"));
        }
    }

    //For Argument Converter
    static class PasswordConverter implements ArgumentConverter {
        @Override
        public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {

            if(o instanceof String) {
                return ((String) o).replaceAll("12345", "67890");
            }

            fail("Cannot replace string");

            return null;
        }
    }
}
