package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class demoUtilsTest{

    private DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach(){
        demoUtils=new DemoUtils();
    }

    @Test
    @DisplayName(" Test equals and not equals")
    void testEqualsAndNotEquals() {

        assertEquals(6, demoUtils.add(2, 4), "Value should be 6");
        assertNotEquals(8, demoUtils.add(2, 4), "value should not equal to 8");

    }

    @Test
    @DisplayName(" Test not null and null")
    void testNotNull() {

        String str1 = null;
        String str2 = "luv2code";

        assertNotNull(demoUtils.checkNull(str2), "Object must not be null");
        assertNull(demoUtils.checkNull(str1), "Object must be null");
    }

    @Test
    @DisplayName("Test same And Not Same")
    void testSameAndNotSame(){
        String str="luv2code";

        assertSame(demoUtils.getAcademy(),demoUtils.getAcademyDuplicate(),"Object should refer to same object");
        assertNotSame(str,demoUtils.getAcademyDuplicate(),"Object should not refer to object");
    }

    @Test
    @DisplayName("Test true and false")
    void testTrueAndFalse(){

        assertTrue(demoUtils.isGreater(3,2),"Value 1st parameter should be greater than the 2nd parameter");
        assertFalse(demoUtils.isGreater(2,3), "Value 1st parameter should not be greater than the 2nd parameter ");
    }

    @Test
    @DisplayName("Array equals")
    void testArrayEquals(){

        String[] stringArray={"A","B","C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }

    @Test
    @DisplayName("test iterable equals")
    void testIterableEquals(){
        List<String> list = List.of("luv", "2", "code");

        assertIterableEquals(list, demoUtils.getAcademyInList(), "Iterable array should be same");
    }

    @Test
    @DisplayName("Test line match")
    void testLineMatch(){
        List<String> list = List.of("luv", "2", "code");

        assertLinesMatch(list, demoUtils.getAcademyInList(), "Lines should be the same");
    }

    @Test
    @DisplayName("Test throws and does not throw")
    void testThrowsAndDoesNotThrow(){

        assertThrows(Exception.class, ()->{demoUtils.throwException(-1);}, "Should throw exception");
        assertDoesNotThrow(()->{demoUtils.throwException(1);},"Should not throw exception");
    }

    @Test
    @DisplayName("Test timeout")
    void testTimeout(){
        assertTimeoutPreemptively(Duration.ofSeconds(5), ()->{ demoUtils.checkTimeout();},
                "Timeout should not be longer than 5");
    }

    @Test
    @DisplayName("Test multiply")
    void testMultiply(){
        assertEquals(8, demoUtils.multiply(2,4), "Value should be equal to 8");
    }













/*
    @AfterEach
    void setupAfterEach(){
        System.out.println("Running @AfterEach");
    }

    @BeforeAll
    static void setupBeforeAll(){
        System.out.println("@Before executes only once");
    }

    @AfterAll
    static void setupAfterAll(){
        System.out.println("\n@After executes only once");
    }
 */

}
