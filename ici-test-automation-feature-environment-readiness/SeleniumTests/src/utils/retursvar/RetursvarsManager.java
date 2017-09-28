package utils.retursvar;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;
import utils.integrationfiles.IntegrationFileSender;
import utils.tools.Etc;

public class RetursvarsManager extends TestContext implements GeneratesRetursvarsfil {
    PropertyProvider propertyProvider;

    private static final String retursvarsfilSti = "C:\\temp";

    public RetursvarsManager(PropertyProvider propertyProvider) {
        super();
        this.propertyProvider = propertyProvider;
    }

    public File opretRetursvarFil(Retursvar retursvar) throws IOException {
        // sets the psrm date to the retursvar
        setActualPsrmDate(retursvar);
        // sets the customer type based on customerID
        determineCustomerType(retursvar.getCustomerID(), retursvar);

        return generateFile(retursvar);

    }

    public void setActualPsrmDate(Retursvar retursvar) {
        setPropertyProvider(new PropertyProviderImpl(this));
        doMaximizeWindow();
        doLogin();
        retursvar.setPsrmDate(MO_Utilities.getPsrmDateTime(this));
        // setPsrmDate(null);
        // doLogout();
    }

    public void indsendRetursvarsFil(File file, RetursvarType type) {
        new IntegrationFileSender(propertyProvider).indsendFil(file, type.fileTypeTextForInput());
    }

    private File generateFile(Retursvar retursvar)
            throws IOException {

        String paymentFileContent = generateFileContent(retursvar);
        String fileName = retursvar.getRetursvarType() + "_" + retursvar.getUniqueID() + ".xml";
        return Etc.createFile(retursvarsfilSti, fileName, paymentFileContent);
    }

    private void determineCustomerType(String CustomerID, Retursvar retursvar) {
        // defines customerType based on the length of the ID.
        if (CustomerID.length() == 8) {
            retursvar.setCustomerType(CustomerType.FORDRINGSHAVER);
        } else if (CustomerID.length() == 10) {
            retursvar.setCustomerType(CustomerType.SKYLDNER);
        } else {
            retursvar.setCustomerType(null);
        }

    }

    private String generateFileContent(Retursvar retursvar) {
        String newline = System.getProperty("line.separator"); // Newline
        String customerID = retursvar.getCustomerID();
        RetursvarType svartype = retursvar.getRetursvarType();
        String[] psrmDateTime = retursvar.getPsrmDate();
        String justeringsID = retursvar.getJusteringsID();
        String messageID = retursvar.getMessageID();
        CustomerType customerType = retursvar.getCustomerType();

        DecimalFormat df = new DecimalFormat("0.000");
        String paymentAmount = df.format(retursvar.getBeloeb());
        paymentAmount = paymentAmount.replace(".", ",");
        paymentAmount = paymentAmount.replace(",", "");
        
        String psrmDate = psrmDateTime[0]; // format: 01-09-2017
        String psrmTime = psrmDateTime[1]; // format: 16:39:37

        // reformat the date format
        String day = psrmDate.substring(0, 2);
        String month = psrmDate.substring(3, 5);
        String year = psrmDate.substring(6, 10);
        String hh = psrmTime.substring(0, 2);
        String mm = psrmTime.substring(3, 5);
        String ss = psrmTime.substring(6, 8);

        // String psrmDateyyyyMMdd = year + month + day;
        String psrmDateyyyyMMddThhmmss = year + "-" + month + "-" + day + "T" + hh + ":" + mm + ":" + ss;
        String psrmDateyyyyMMdd = year + "-" + month + "-" + day;

        switch (customerType) {
        case SKYLDNER:
            switch (svartype) {
            // TO_DO : Check om det er KVITTERING0 eller KVITTERING1
            case KVITTERING:
                // skyldner udgave
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSReceipt1 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSReceipt1.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Kvitteringssvar 1 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:Action>ACPT</ebms:Action>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS KVITTERING1           3900</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "</nks:NKSReceipt1>";
            case RETURSVAR2_ACCEPT:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse2 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse2.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Retursvar 2 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS2C RETURSVAR2 0000003901</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR2 0000003901</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + ":14</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "<swift:GrpSts>ACPT</swift:GrpSts>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse2>";
            case RETURSVAR2_REJECT:
                // This file has not officially been provided nor tested. Made
                // by Jeffrey
                // TO-DO
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse2 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse2.xsd>"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Retursvar 2 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS2C RETURSVAR2 0000003902</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR2 0000003902</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtT>" + psrmDateyyyyMMddThhmmss + ":14</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "<swift:GrpSts>RJCT</swift:GrpSts>" + newline +
                        "<swift:StsRsn>0002</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>INVALID BETALINGSDATO</swift:AddtlInf>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse2>";
            case RETURSVAR5:
                throw new ArithmeticException("Retursvar 5 is not possible with a private person as a customer");
            case RETURSVAR7:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse7 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse7.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR7 0000003001</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>RJCT</swift:TxSts>" + newline +
                        "<swift:StsRsn>RJCT</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>Ingen nemkonto/specifik konto. Myndigheden har valgt betalingen retur</swift:AddtlInf>"
                        + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>1</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:PrvtId>" + newline +
                        "<swift:SclSctyNb>" + customerID + "</swift:SclSctyNb>" + newline +
                        "</swift:PrvtId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse7>";
            case RETURSVAR8:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse8 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse8.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR8 0000003902</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:ReqdExctnDt>2017-09 1</swift:ReqdExctnDt>" + newline +
                        "<swift:Dbtr>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:BkPtyId>248348</swift:BkPtyId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:Dbtr>" + newline +
                        "<swift:DbtrAcct>" + newline +
                        "<swift:Id>" + newline +
                        "<swift:BBAN>81090001000539</swift:BBAN>" + newline +
                        "</swift:Id>" + newline +
                        "</swift:DbtrAcct>" + newline +
                        "<swift:DbtPurp>" + messageID + "</swift:DbtPurp>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>ACPT</swift:TxSts>" + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>1</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:PrvtId>" + newline +
                        "<swift:SclSctyNb>" + customerID + "</swift:SclSctyNb>" + newline +
                        "</swift:PrvtId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "<swift:CdtrAcct>" + newline +
                        "<swift:Id>" + newline +
                        "<swift:BBAN>11990004227700</swift:BBAN>" + newline +
                        "</swift:Id>" + newline +
                        "</swift:CdtrAcct>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse8>";
            case RETURSVAR9:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse9 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse9.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR9 0000003714</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>RJCT</swift:TxSts>" + newline +
                        "<swift:StsRsn>0001</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>FEJL I LIN NR. 000001, SEQ NR. 000001                                 FEJL I JYSKE BANK KONTONUMMER</swift:AddtlInf>"
                        + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>1</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:SclSctyNb>" + customerID + "</swift:SclSctyNb>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse9>";
            default:
                return null;
            }
        case FORDRINGSHAVER:
            switch (svartype) {
            case KVITTERING:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSReceipt1 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSReceipt1.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Kvitteringssvar 1 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:Action>ACPT</ebms:Action>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS KVITTERING1           3702</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>\"" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>\"" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "</nks:NKSReceipt1>";
            case RETURSVAR2_REJECT:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse2 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse2.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Retursvar 2 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS2C RETURSVAR2 0000003702</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR2 0000003702</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + ":14</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "<swift:GrpSts>RJCT</swift:GrpSts>" + newline +
                        "<swift:StsRsn>0002</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>INVALID BETALINGSDATO</swift:AddtlInf>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse2>";
            case RETURSVAR2_ACCEPT:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse2 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse2.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Retursvar 2 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS2C RETURSVAR2 0000003702</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR2 0000003701</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + ":14</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "<swift:GrpSts>ACPT</swift:GrpSts>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse2>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse2>";
            case RETURSVAR5:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse5 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse5.xsd\">"
                        + newline +
                        "<nks:MessageHeader ebms:id=\"Retursvar 5 NKS2C\" ebms:version=\"2.0\">" + newline +
                        "<ebms:From>" + newline +
                        "<ebms:PartyId>NKSTEST</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798009811110</ebms:PartyId>" + newline +
                        "</ebms:From>" + newline +
                        "<ebms:To>" + newline +
                        "<ebms:PartyId>SKATID</ebms:PartyId>" + newline +
                        "<ebms:PartyId>5798000033788</ebms:PartyId>" + newline +
                        "</ebms:To>" + newline +
                        "<ebms:MessageData>" + newline +
                        "<ebms:MessageId>NKS2C RETURSVAR5 0000003111</ebms:MessageId>" + newline +
                        "<ebms:Timestamp>" + psrmDateyyyyMMddThhmmss + "</ebms:Timestamp>" + newline +
                        "<ebms:RefToMessageId>" + messageID + "</ebms:RefToMessageId>" + newline +
                        "</ebms:MessageData>" + newline +
                        "</nks:MessageHeader>" + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR5 0000003111</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + ":14</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "<swift:GrpSts>RJCT</swift:GrpSts>" + newline +
                        "<swift:AddtlInf>BETALING(ER) STANDSET</swift:AddtlInf>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "</nks:NKSResponse5>";
            case RETURSVAR7:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse7 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse7.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR7 0000003001</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>RJCT</swift:TxSts>" + newline +
                        "<swift:StsRsn>RJCT</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>Ingen nemkonto/specifik konto. Myndigheden har valgt betalingen retur</swift:AddtlInf>"
                        + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>0</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>" + customerID + "</swift:Id>" + newline +
                        "<swift:Issr>CVR</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse7>";
            case RETURSVAR8:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse8 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse8.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR8 0000003701</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:ReqdExctnDt>" + psrmDateyyyyMMdd + "</swift:ReqdExctnDt>" + newline +
                        "<swift:Dbtr>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:BkPtyId>248348</swift:BkPtyId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:Dbtr>" + newline +
                        "<swift:DbtrAcct>" + newline +
                        "<swift:Id>" + newline +
                        "<swift:BBAN>81090001000539</swift:BBAN>" + newline +
                        "</swift:Id>" + newline +
                        "</swift:DbtrAcct>" + newline +
                        "<swift:DbtPurp>" + messageID + "</swift:DbtPurp>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>ACPT</swift:TxSts>" + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>1</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>" + customerID + "</swift:Id>" + newline +
                        "<swift:Issr>CVR</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "<swift:CdtrAcct>" + newline +
                        "<swift:Id>" + newline +
                        "<swift:BBAN>52210000999892</swift:BBAN>" + newline +
                        "</swift:Id>" + newline +
                        "</swift:CdtrAcct>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse8>";
            case RETURSVAR9:
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newline +
                        "<nks:NKSResponse9 xmlns:nks=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/\" xmlns:ebms=\"http://rep.oio.dk/oes.dk/nemkonto.ebms/xml/schemas/2006/05/01/\" xmlns:swift=\"http://rep.oio.dk/oes.dk/nemkonto.swift/xml/schemas/2006/05/01/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/ http://rep.oio.dk/oes.dk/nemkonto/xml/schemas/2006/05/01/NKS_NKSResponse9.xsd\">"
                        + newline +
                        "<nks:GnlInf>" + newline +
                        "<swift:PmtInitnStsId>NKS2C RETURSVAR9 0000003722</swift:PmtInitnStsId>" + newline +
                        "<swift:CreDtTm>" + psrmDateyyyyMMddThhmmss + "</swift:CreDtTm>" + newline +
                        "<swift:InitgPty>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>815213</swift:Id>" + newline +
                        "<swift:Issr>ADMID</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:InitgPty>" + newline +
                        "</nks:GnlInf>" + newline +
                        "<nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<swift:GrpId>" + messageID + "</swift:GrpId>" + newline +
                        "<swift:OrgnlMsgTp>NKSBetaling</swift:OrgnlMsgTp>" + newline +
                        "</nks:OrgnlGrpRefInfAndSts>" + newline +
                        "<nks:OrgnlPmtInf>" + newline +
                        "<swift:OrgnlTxRefInfAndSts>" + newline +
                        "<swift:PmtId>" + newline +
                        "<swift:InstrId>" + justeringsID + "</swift:InstrId>" + newline +
                        "<swift:EndToEndId>" + justeringsID + "</swift:EndToEndId>" + newline +
                        "</swift:PmtId>" + newline +
                        "<swift:TxSts>RJCT</swift:TxSts>" + newline +
                        "<swift:StsRsn>0001</swift:StsRsn>" + newline +
                        "<swift:AddtlInf>FEJL I LIN NR. 000001, SEQ NR. 000001                                 FEJL I JYSKE BANK KONTONUMMER</swift:AddtlInf>"
                        + newline +
                        "<swift:OrgnlTxInf>" + newline +
                        "<swift:Amt>" + newline +
                        "<swift:InstdAmt Ccy=\"DKK\">" + paymentAmount + "</swift:InstdAmt>" + newline +
                        "</swift:Amt>" + newline +
                        "<swift:IncompletePaymentIndicator>1</swift:IncompletePaymentIndicator>" + newline +
                        "<swift:Cdtr>" + newline +
                        "<swift:OrgId>" + newline +
                        "<swift:PrtryId>" + newline +
                        "<swift:Id>" + customerID + "</swift:Id>" + newline +
                        "<swift:Issr>CVR</swift:Issr>" + newline +
                        "</swift:PrtryId>" + newline +
                        "</swift:OrgId>" + newline +
                        "</swift:Cdtr>" + newline +
                        "</swift:OrgnlTxInf>" + newline +
                        "</swift:OrgnlTxRefInfAndSts>" + newline +
                        "</nks:OrgnlPmtInf>" + newline +
                        "</nks:NKSResponse9>";
            default:
                return null;
            }
            // in case not Fordringshaver or Skyldner
        default:
            return null;
        }

    }

}
