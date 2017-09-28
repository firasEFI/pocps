package utils;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jemk on 02-06-2017.
 */
public class UtilTests {

    @Test
    public void testGetBaseurl() {
        PropertyProviderImpl p = new PropertyProviderImpl();
        Assert.assertEquals(p.getUrl(), "https://10.215.49.12:6007/reg02/loginPage.jsp?normal");
    }

    @Test
    public void testGetUsername() {
        PropertyProviderImpl p = new PropertyProviderImpl();
        Assert.assertEquals(p.getUsername(), "GEB");
    }

    @Test
    public void testGetPassword() {
        PropertyProviderImpl p = new PropertyProviderImpl();
        Assert.assertEquals(p.getPassword(), "netcompany-123");
    }
    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
