package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until jira #123 is resolved")
    void basicTest(){
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("Test for windows only")
    void testForWindowsOnly(){
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @DisplayName("Test for mac only")
    void testForMacOnly(){
    }

    @Test
    @DisplayName("Test for mac and windows only")
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForMacAndWindowsOnly(){
    }

    @Test
    @DisplayName("Test for linux only")
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly(){
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @DisplayName("Test for only java 17")
    void testForOnlyJava17(){

    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_8, max=JRE.JAVA_18)
    @DisplayName("Test for java 13 to java 17")
    void testForJava13ToJava17(){
    }


    @Test
    @DisplayName("Test for environment")
    @EnabledIfEnvironmentVariable(named="LUV2CODE_ENV", matches="DEV")
    void testOnlyForDevEnvironment(){

    }

    @Test
    @DisplayName("Test for system property")
    @EnabledIfSystemProperty(named="LUV2CODE_SYS_PROP", matches="CI_CD_DEPLOY")
    void testOnlyForSystemProperty(){

    }

}
