package utils.retursvar;

import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import utils.tools.Etc;

public class Retursvar extends TestContext {
    PropertyProvider propertyProvider;
    private double beloeb;

    private String[] psrmDate = null;

    private String customerID;

    private String justeringsID;

    private String messageID;

    private String uniqueFileID;

    private CustomerType customerType;

    private RetursvarType retursvarType;

    public RetursvarType getRetursvarType() {
        return retursvarType;
    }

    public void setRetursvarType(RetursvarType retursvarType) {
        this.retursvarType = retursvarType;
    }

    /**
     * 
     * @param retursvarType
     * @param beloeb
     * @param customerID
     * @param justeringsID
     * @param messageID
     * @param uniqueFileID
     */
    public Retursvar(RetursvarType retursvarType, double beloeb, String customerID, String justeringsID,
            String messageID, String uniqueID) {
        super();
        this.retursvarType = retursvarType;
        this.beloeb = beloeb;
        this.customerID = customerID;
        this.justeringsID = justeringsID;
        this.messageID = messageID;
        this.uniqueFileID = uniqueID;
    }

    /**
     * Constructor with automatic unique file id
     * 
     * @param retursvarType
     * @param beloeb
     * @param customerID
     * @param justeringsID
     * @param messageID
     */
    public Retursvar(RetursvarType retursvarType, double beloeb, String customerID, String justeringsID,
            String messageID) {
        this(retursvarType, beloeb, customerID, justeringsID, messageID, uniqueIdRand());
    }

    public String getUniqueID() {
        return uniqueFileID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueFileID = uniqueID;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public double getBeloeb() {
        return beloeb;
    }

    public void setBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }

    public String[] getPsrmDate() {
        return psrmDate;
    }

    public void setPsrmDate(String[] psrmDate) {
        this.psrmDate = psrmDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getJusteringsID() {
        return justeringsID;
    }

    public void setJusteringsID(String justeringsID) {
        this.justeringsID = justeringsID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    private static String uniqueIdRand() {
        // set random ID if not specified by user. Max char for unique ID = 7.
        // this needs to be tested if it also works for retursvar
        return Etc.generateNDigitNumberAsString(7);
    }

}
