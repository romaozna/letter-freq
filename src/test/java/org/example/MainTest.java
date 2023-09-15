package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private static ByteArrayOutputStream testOut;

    @BeforeAll
    public static void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterAll
    public static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void shouldThrowIOException() {
        final String testString = "not_found.txt";
        provideInput(testString);

        Exception exception = assertThrows(FileNotFoundException.class, Main::getInput);

        String expectedMessage = "Не удается найти указанный файл";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldCountSixWordsFromFile() {
        final String testString = "sixsix.txt";
        provideInput(testString);

        String expectedMessage = "six - 6";

        assertDoesNotThrow(() -> Main.main(new String[0]));

        assertTrue(getOutput().contains(expectedMessage));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
}
