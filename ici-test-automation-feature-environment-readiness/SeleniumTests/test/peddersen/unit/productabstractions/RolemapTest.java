package peddersen.unit.productabstractions;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import productabstractions.RoleMap;
import productabstractions.RoleMapImpl;
import productabstractions.Roles;

public class RolemapTest {
    @Test(groups="unit", expectedExceptions = RuntimeException.class, enabled = true)
    public void constructorNegativeTest1() {
        new RoleMapImpl(true, true, true, true, true, true, true, true, true);
    }

    @Test(groups="unit", expectedExceptions = RuntimeException.class, enabled = true)
    public void constructorNegativeTest2() {
        new RoleMapImpl(true, false, true, false, false, false, false, false, false);
    }

    @Test(groups="unit", expectedExceptions = RuntimeException.class, enabled = true)
    public void constructorNegativeTest3() {
        new RoleMapImpl(false, false, false, false, false, false, false, false, false);
    }

    @Test(groups="unit")
    public void constructorTest() {
        new RoleMapImpl(false, false, true, false, false, false, false, false, true);
    }

    @Test(groups="unit")
    public void toStringTest() {
        RoleMap map = new RoleMapImpl(false, false, true, false, false, false, false, false, true);
        assertEquals(map.toString(), "001000001 Rolemap");
    }

    @Test(groups="unit")
    public void constructorUsingRolesTest() {
        RoleMap map = new RoleMapImpl(Roles.SAGSBEHANDLER_GENEREL, Roles.SAGSBEHANDLER_GODKENDER);
        assert map.isSagsbehandlerGenerel() && map.isSagsbehandlerGodkender();
        assert !map.isFunktionsleder();
    }

    @Test(groups="unit", expectedExceptions = RuntimeException.class)
    public void constructorUsingRolesTestNegative() {
        new RoleMapImpl(Roles.SAGSBEHANDLER_GENEREL, Roles.SE_SAGSBEHANDLER_MED_NOTER);
    }

    @Test(groups="unit")
    public void testStringConstructor() {
        RoleMap rm = new RoleMapImpl("110000000");
        assert rm.isSagsbehandlerGodkender();
        assert rm.isSagsbehandlerGodkender();
        assert !rm.isBetalingssagsbehandler();
        assert !rm.isBetalingssagsbehandlerGodkender();
        assert !rm.isFordringshaversagsbehandler();
        assert !rm.isSeSagsbehandlerMedNoter();
        assert !rm.isFunktionsleder();
        assert !rm.isSystemadministrator();
        assert !rm.isVipSagsbehandler();
    }
}
