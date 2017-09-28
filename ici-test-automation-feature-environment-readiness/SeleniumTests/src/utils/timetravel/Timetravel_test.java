package utils.timetravel;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.testng.annotations.Test;

import utils.PropertyProviderImpl;

public class Timetravel_test {
    
    @Test
    public void test() throws ParseException, IOException {
        //set timetravel to today's date
        new Timetravel(new PropertyProviderImpl()).to(new Date());
    }
}
