package findus_datamodelWarehouses;

import static findus_rabbitMQ.CprWarehouse.prefs;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.prefs.BackingStoreException;

import findus_datamodels.CprSkyldnerModel;
import findus_rabbitMQ.CprWarehouse;

public class SkyldnerWarehouse {

    public static CprSkyldnerModel getSkyldner_Val1332() {
        return getCommonTestSkyldner("test", "person", "0505823737", "5082018127");
    }

    public static CprSkyldnerModel getSkyldner_Val727_1() {
        return getCommonTestSkyldner("test", "person", "0505823737", "5082018127");
    }

    public static CprSkyldnerModel getSkyldner_Val727_2() {
        return getCommonTestSkyldner("test", "person", "0505823737", "5082018127");
    }

    public static CprSkyldnerModel getSkyldner_testPerson() {
         return getCommonTestSkyldner("","",getCprNumber(),""); //Til
        // udvikling
        //return getCommonTestSkyldner("", "", "180893-0706", "");
    }

    public static CprSkyldnerModel skylder_aTEST22() {
        return getCommonTestSkyldner("Test", "Person", "0505630998", "");
    }

    public static CprSkyldnerModel skylder_aTEST26() {
        return getCommonTestSkyldner("Test", "Person", "0505630998", "");
    }

    public static CprSkyldnerModel skylder_aTEST30() {
        return getCommonTestSkyldner("Test", "Person", "0505630998", "");
    }

    public static CprSkyldnerModel skyldner_Val3780() {
        CprSkyldnerModel skyldner = new CprSkyldnerModel();
        skyldner.setCprNummer("0505682912");
        return skyldner;
    }

    public static CprSkyldnerModel getSkyldner_test83() {
        return getCommonTestSkyldner("", "", "0505720490", "");
    }

    public static CprSkyldnerModel getSkyldner_test84() {
        return getCommonTestSkyldner("", "", "0505720490", "");
    }

    public  static CprSkyldnerModel getSkyldner_test86() {
        return getCommonTestSkyldner("","","0505720490","");
    }

    public  static CprSkyldnerModel getSkyldner_test87() {
        return getCommonTestSkyldner("","","0505720490","");
    }

    //Fælles metode til at få en skyldner med specifikke data
    private static CprSkyldnerModel getCommonTestSkyldner(String fornavn, String efternavn, String cprnr, String interntId){
        CprSkyldnerModel skyldner = new CprSkyldnerModel();
        skyldner.setFornavn(fornavn);
        skyldner.setEfternavn(efternavn);
        skyldner.setCprNummer(cprnr);
        skyldner.setInterntID(interntId);
        return skyldner;
    }

    private static String getCprNumber() {
        CprWarehouse cprWarehouse = new CprWarehouse();
        String cprNumber = "";
        try {
            cprNumber = cprWarehouse.getStandardCprnumber();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return cprNumber;
    }

    public static void skyldnerUnuseable() {
        System.out.println("CPR now unusable, should get new");

        try {
            prefs.removeNode();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

    }
}
