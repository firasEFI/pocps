package icisel.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import icisel.config.PropertyProvider;

/**
 * Created by jemk on 02-06-2017.
 */
public class UtilTests {

    @Test
    public void testGetBaseurl(){
        PropertyProvider p = new PropertyProvider();
        Assert.assertEquals(p.getUrl(), "https://10.215.49.12:6007/reg02/loginPage.jsp?normal");
    }

    @Test
    public void testGetUsername(){
        PropertyProvider p = new PropertyProvider();
        Assert.assertEquals(p.getUsername(), "GEB");
    }
    @Test
    public void testGetPassword(){
        PropertyProvider p = new PropertyProvider();
        Assert.assertEquals(p.getPassword(), "netcompany-123");
    }
}
