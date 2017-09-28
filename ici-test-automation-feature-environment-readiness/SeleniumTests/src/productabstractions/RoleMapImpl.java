package productabstractions;

import java.util.InputMismatchException;

public class RoleMapImpl implements RoleMap {
    private final int numRoles = 9;
    private boolean[] booleans = new boolean[9];

    /**
     * Exactly one of the following must be true:
     * <ul>
     * <li>sagsbehandlerGenerel</li>
     * <li>betalingssagsbehandler</li>
     * <li>seSagsbehandlerMedNoter</li>
     * </ul>
     * 
     * @param sagsbehandlerGenerel
     * @param sagsbehandlerGodkender
     * @param betalingssagsbehandler
     * @param betalingssagsbehandlerGodkender
     * @param fordringshaversagsbehandler
     * @param seSagsbehandlerMedNoter
     * @param funktionsleder
     * @param systemadministrator
     * @param vipSagsbehandler
     */
    public RoleMapImpl(boolean sagsbehandlerGenerel, boolean sagsbehandlerGodkender,
            boolean betalingssagsbehandler, boolean betalingssagsbehandlerGodkender,
            boolean fordringshaversagsbehandler, boolean seSagsbehandlerMedNoter, boolean funktionsleder,
            boolean systemadministrator, boolean vipSagsbehandler) {
        super();

        validateExactlyOne(sagsbehandlerGenerel, betalingssagsbehandler, seSagsbehandlerMedNoter);

        setSagsbehandlerGenerel(sagsbehandlerGenerel);
        setSagsbehandlerGodkender(sagsbehandlerGodkender);
        setBetalingssagsbehandler(betalingssagsbehandler);
        setBetalingssagsbehandlerGodkender(betalingssagsbehandlerGodkender);
        setFordringshaversagsbehandler(fordringshaversagsbehandler);
        setSeSagsbehandlerMedNoter(seSagsbehandlerMedNoter);
        setFunktionsleder(funktionsleder);
        setSystemadministrator(systemadministrator);
        setVipSagsbehandler(vipSagsbehandler);
    }

    /**
     * 
     * @param roleMapAsBinaryString
     *            e.g. 100100010
     */
    public RoleMapImpl(String roleMapAsBinaryString) {
        this(asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(0)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(1)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(2)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(3)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(4)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(5)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(6)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(7)),
                asBooleanCharOneOrZero(roleMapAsBinaryString.charAt(8)));
        if (roleMapAsBinaryString.length() != numRoles) {
            throw new InputMismatchException("Length of map must be exactly 9, e.g. 100100010.");
        }
    }

    public void validateExactlyOne(boolean sagsbehandlerGenerel, boolean betalingssagsbehandler,
            boolean seSagsbehandlerMedNoter) {
        if (!(exactlyOne(sagsbehandlerGenerel, betalingssagsbehandler, seSagsbehandlerMedNoter))) {
            failValidateExactlyOne();
        }
    }

    public void failValidateExactlyOne() {
        throw new RuntimeException(
                "Must choose exactly one of [Sagsbehandler - generel], [Betalingssagsbehandler], [Se-sagsbehandler med noter]");
    }

    public RoleMapImpl(Roles... roles) {
        super();
        validateExactlyOneMainRole(roles);

        for (Roles role : roles) {
            booleans[role.ordinal()] = true;
        }
    }

    private void validateExactlyOneMainRole(Roles[] roles) {
        int numMainRoles = 0;
        for (Roles role : roles) {
            int ordinal = role.ordinal();
            if (ordinal == 0 || ordinal == 2 || ordinal == 5) {
                numMainRoles++;
            }
        }
        if (!(numMainRoles == 1)) {
            failValidateExactlyOne();
        }

    }

    private boolean exactlyOne(boolean b1, boolean b2,
            boolean b3) {
        return asIntOneOrZero(b1) + asIntOneOrZero(b2) + asIntOneOrZero(b3) == 1;
    }

    private int asIntOneOrZero(boolean isTrue) {
        return isTrue ? 1 : 0;
    }

    private char asCharOneOrZero(boolean isTrue) {
        return isTrue ? '1' : '0';
    }

    private static boolean asBooleanCharOneOrZero(char c) {
        return c == '1';
    }

    @Override
    public boolean isSagsbehandlerGenerel() {
        return booleans[0];
    }

    @Override
    public boolean isSagsbehandlerGodkender() {
        return booleans[1];
    }

    @Override
    public boolean isBetalingssagsbehandler() {
        return booleans[2];
    }

    @Override
    public boolean isBetalingssagsbehandlerGodkender() {
        return booleans[3];
    }

    @Override
    public boolean isFordringshaversagsbehandler() {
        return booleans[4];
    }

    @Override
    public boolean isSeSagsbehandlerMedNoter() {
        return booleans[5];
    }

    @Override
    public boolean isFunktionsleder() {
        return booleans[6];
    }

    @Override
    public boolean isSystemadministrator() {
        return booleans[7];
    }

    @Override
    public boolean isVipSagsbehandler() {
        return booleans[8];
    }

    public void setSagsbehandlerGenerel(boolean sagsbehandlerGenerel) {
        booleans[0] = sagsbehandlerGenerel;
    }

    public void setSagsbehandlerGodkender(boolean sagsbehandlerGodkender) {
        booleans[1] = sagsbehandlerGodkender;
    }

    public void setBetalingssagsbehandler(boolean betalingssagsbehandler) {
        booleans[2] = betalingssagsbehandler;
    }

    public void setBetalingssagsbehandlerGodkender(boolean betalingssagsbehandlerGodkender) {
        booleans[3] = betalingssagsbehandlerGodkender;
    }

    public void setFordringshaversagsbehandler(boolean fordringshaversagsbehandler) {
        booleans[4] = fordringshaversagsbehandler;
    }

    public void setSeSagsbehandlerMedNoter(boolean seSagsbehandlerMedNoter) {
        booleans[5] = seSagsbehandlerMedNoter;
    }

    public void setFunktionsleder(boolean funktionsleder) {
        booleans[6] = funktionsleder;
    }

    public void setSystemadministrator(boolean systemadministrator) {
        booleans[7] = systemadministrator;
    }

    public void setVipSagsbehandler(boolean vipSagsbehandler) {
        booleans[8] = vipSagsbehandler;
    }

    @Override
    public String toString() {
        char[] name = new char[9];
        for (int i = 0; i < name.length; i++) {
            name[i] = asCharOneOrZero(booleans[i]);
        }
        return String.valueOf(name) + " Rolemap";
    }

}
