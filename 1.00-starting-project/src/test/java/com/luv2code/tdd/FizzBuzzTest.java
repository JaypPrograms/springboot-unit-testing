package com.luv2code.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class FizzBuzzTest {


    @Test
    @DisplayName("Divisible by three")
    void testForDivisibleByThree(){

        String expected="Fizz";

        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");

    }

    @Test
    @DisplayName("Divisible by Five")
    void testForDivisibleByFive(){

        String expected="Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @Test
    @DisplayName("Divisible by Three and Five")
    void testForDivisibleByFiveAndThree(){

        String expected="FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @Test
    @DisplayName("Not divisible by Three and Five")
    void testNotDivisibleByThreeOrFive(){

        int number=2;
        assertEquals(String.valueOf(number), FizzBuzz.compute(number), "Should return same number");
    }

    @DisplayName("Testing with small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    void testSmallDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value),"Value should be "+expected);
    }

    @DisplayName("Testing with medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    void testMediumDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value),"Value should be "+expected);
    }

    @DisplayName("Testing with large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    void testLargeDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value),"Value should be "+expected);
    }




}
