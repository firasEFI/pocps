package findus_pageobjects;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilsTests {

    @Test(groups = "unit")
    public void formatDouble_ShouldTruncate() {
        double input = 3.4444;
        String result = Utils.formatDouble(input);

        Assert.assertEquals(result, "3,44");
    }

    @Test(groups = "unit")
    public void formatDouble_ShouldNotRoundUp() {
        double input = 3.445;
        String result = Utils.formatDouble(input);

        Assert.assertEquals(result, "3,44");
    }

    @Test(groups = "unit")
    public void formatCalendar_ShouldReturnCorrectResult() {
        Calendar input = Calendar.getInstance();

        input.set(Calendar.YEAR, 2010);
        input.set(Calendar.MONTH, 4);
        input.set(Calendar.DAY_OF_MONTH, 20);

        String result = Utils.formatDate(input);

        Assert.assertEquals(result, "20-05-2010");
    }
}
