package com.st.java23testingadvanced;

import com.st.java23testingadvanced.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

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






    //For Method Source
    static Stream<Arguments> getPasswords() {
        return Stream.of(Arguments.of("12835smjdbf"), Arguments.of("aksdubiua238sf"));
    }
}
