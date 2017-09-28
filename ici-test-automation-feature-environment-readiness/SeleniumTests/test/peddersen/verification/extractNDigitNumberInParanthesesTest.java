package peddersen.verification;

import java.util.InputMismatchException;

import org.testng.Assert;
import org.testng.annotations.Test;

import modules.MO_Fordring;

public class extractNDigitNumberInParanthesesTest {

    @Test
    public void t1() {
        String inputString = "DR - Medielicens (til DR), DR - Medielicens (til DR), 14-08-2017, 28204667252467 (0996959737) - 999,98kr.";
        Assert.assertEquals(test(inputString, 10), "0996959737");
    }

    @Test
    public void t2() {
        String inputString = ") (123)";
        Assert.assertEquals(test(inputString, 3), "123");
    }

    @Test
    public void t3() {
        String inputString = "() (12) (123) (1234)";
        try {
            Assert.assertEquals(test(inputString, 0), "");
        } catch (InputMismatchException e) {
            e.printStackTrace();
            return;
        }
        // negative test; fail if no exception
        Assert.fail();

    }

    @Test
    public void t8() {
        String inputString = "() (12) (123) (123b)";
        try {
            Assert.assertEquals(test(inputString, 4), "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        // negative test; fail if no exception
        Assert.fail();

    }

    @Test
    public void t4() {
        String inputString = "() (12) (123) (1234)";
        Assert.assertEquals(test(inputString, 2), "12");
    }

    @Test
    public void t5() {
        String inputString = "() (12) (123) (1234)";
        Assert.assertEquals(test(inputString, 3), "123");
    }

    @Test
    public void t6() {
        String inputString = "() (12) (123) (1234)";
        Assert.assertEquals(test(inputString, 4), "1234");
    }

    @Test
    public void t7() {
        String inputString = "( asdf) (123.5) (12345)";
        Assert.assertEquals(test(inputString, 5), "12345");
    }

    public String test(String inputString, int length) {
        return MO_Fordring.extractNDigitNumberInParantheses(inputString, length);
    }
}
