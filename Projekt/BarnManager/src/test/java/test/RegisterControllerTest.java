package test;

import example.controller.RegisterController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    @ParameterizedTest
    @CsvSource({"User123, true", "_1_, false", "123asd, false", "A1, true"})
    void isValidUsername(String username, boolean expected) {
        boolean actual = RegisterController.isValidUsername(username); //muszáj statikus metódust meghívni, mert különben példányosítja az osztályt
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"1234567, false", "AsdASD, false", "asd123, false", "asd lol, false", "Asdpassword123, true", "Password1234, true"})
    void isValidPassword(String password, boolean expected) {
        boolean actual = RegisterController.isValidPassword(password);
        assertEquals(expected, actual);
    }
}