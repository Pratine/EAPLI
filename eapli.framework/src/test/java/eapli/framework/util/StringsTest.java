/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eapli.framework.util.Strings;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class StringsTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isNullOrEmpty method, of class Validations.
     */
    @Test
    public void testStringWithContentIsNotNullNorEmpty() {
        System.out.println("string with content is not isNullOrEmpty");
        final String text = "abcdef";
        final boolean expResult = false;
        final boolean result = Strings.isNullOrEmpty(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNullOrEmpty method, of class Validations.
     */
    @Test
    public void testNullIsNullOrEmpty() {
        System.out.println("null isNullOrEmpty");
        final String text = null;
        final boolean expResult = true;
        final boolean result = Strings.isNullOrEmpty(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testEmptyStringIsNullOrEmpty() {
        System.out.println("empty string isNullOrEmpty");
        final String text = "";
        final boolean expResult = true;
        final boolean result = Strings.isNullOrEmpty(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testContainsDigitSingleChar() {
        System.out.println("ContainsDigit single char");
        final String text = "1";
        final boolean expResult = true;
        final boolean result = Strings.containsDigit(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testContainsDigitInTheMiddle() {
        System.out.println("ContainsDigit in the middle");
        final String text = "ab1cd";
        final boolean expResult = true;
        final boolean result = Strings.containsDigit(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testContainsDigitInTheBegining() {
        System.out.println("ContainsDigit in the begining");
        final String text = "1cd";
        final boolean expResult = true;
        final boolean result = Strings.containsDigit(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testContainsDigitInTheEnd() {
        System.out.println("ContainsDigit in the end");
        final String text = "ad1";
        final boolean expResult = true;
        final boolean result = Strings.containsDigit(text);
        assertEquals(expResult, result);
    }

    /**
     */
    @Test
    public void testContainsDigitDetectsNonDigit() {
        System.out.println("ContainsDigit without digits");
        final String text = "dd";
        final boolean expResult = false;
        final boolean result = Strings.containsDigit(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyDigitsWithOnlyLetters() {
        System.out.println("IsOnlyDigits with only letters");
        final String text = "sd";
        final boolean expResult = false;
        final boolean result = Strings.isOnlyDigits(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyDigitsWithLettersAndDigits() {
        System.out.println("IsOnlyDigits with letters and digits");
        final String text = "s12d";
        final boolean expResult = false;
        final boolean result = Strings.isOnlyDigits(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyDigitsWithOnlyDigits() {
        System.out.println("IsOnlyDigits with letters and digits");
        final String text = "213";
        final boolean expResult = true;
        final boolean result = Strings.isOnlyDigits(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyLettersWithOnlyDigits() {
        System.out.println("IsOnlyLetters only digits");
        final String text = "213";
        final boolean expResult = false;
        final boolean result = Strings.isOnlyLetters(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyLettersWithDigitsAndLetters() {
        System.out.println("IsOnlyLetters only digits");
        final String text = "2sa13";
        final boolean expResult = false;
        final boolean result = Strings.isOnlyLetters(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOnlyLettersWithOnlyLetters() {
        System.out.println("IsOnlyLetters only letters");
        final String text = "asd";
        final boolean expResult = true;
        final boolean result = Strings.isOnlyLetters(text);
        assertEquals(expResult, result);
    }
}
