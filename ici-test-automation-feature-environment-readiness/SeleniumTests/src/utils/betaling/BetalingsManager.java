package utils.betaling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import icisel.testng.PropertyProvider;
import icisel.testng.TestContext;
import modules.MO_Utilities;
import utils.PropertyProviderImpl;
import utils.integrationfiles.IntegrationFileSender;
import utils.tools.Etc;

public class BetalingsManager extends TestContext implements GeneratesBetalingsfil {
    PropertyProvider propertyProvider;

    private static final String indbetalingsfilSti = "C:\\temp";

    public BetalingsManager(PropertyProvider propertyProvider) {
        super();
        this.propertyProvider = propertyProvider;
    }

    public File opretBetalingsfil(Betaling betaling, BetalingsfilType type) throws IOException {

        // set random ID for the paymentFile
        betaling.setUniqueId(betaling.uniqueIdRand());
        int adviseringsTekstMaxLength = 24; // max length of adviseringstekst

        // sets the adviseringstekst
        String adviseringsTekst = betaling.getAdviseringstekst();
        if (adviseringsTekst == null || adviseringsTekst == "") {
            // if adviseringstekst is not provided, put in the following:
            betaling.setAdviseringstekst("IK71 0000" + betaling.getOcr());
        } else {
            // Ensure that custom adviseringstekst is not longer than allowed
            if (adviseringsTekst.length() > adviseringsTekstMaxLength) {
                betaling.setAdviseringstekst(betaling.getAdviseringstekst().substring(0, adviseringsTekstMaxLength));
            }
        }

        // generate file (is case-based)
        return generateFile(betaling, type);
    }

    public void indsendBetalingsfil(File file, BetalingsfilType type) {
        IntegrationFileSender integrationFileSender = new IntegrationFileSender(propertyProvider);
        integrationFileSender.indsendFil(file, type.fileTypeTextForInput());
        
    }

    private File generateFile(Betaling indbetaling, BetalingsfilType type)
            throws IOException {

        String paymentFileContent = generateFileContent(indbetaling, type);
        String fileName = type.name() + "_" + indbetaling.getUniqueId();
        return createFile(fileName, paymentFileContent);
    }

    private String generateFileContent(Betaling betaling, BetalingsfilType type) {

        String paymentFileID = betaling.getUniqueId();
        String ocrLine = betaling.getOcr();
        String adviseringsTekst = betaling.getAdviseringstekst();
        Date paymentDate = betaling.getDato();
        String justeringsID = betaling.getJusteringsID();
        String accountNo = betaling.getKontonummer(); // Predefined account
                                                      // number
        String newline = System.getProperty("line.separator"); // Newline
        String paymentAmount = Double.toString(betaling.getBeloebIDkk());
        paymentAmount = paymentAmount.replace(".", ",");

        SimpleDateFormat dateFormatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String paymentDateyyyyMMdd = dateFormatyyyyMMdd.format(paymentDate);

        // Finsta file needs to be the day before
        Calendar dateAdjustment = Calendar.getInstance();
        dateAdjustment.setTime(paymentDate);
        dateAdjustment.add(Calendar.DATE, -1);
        String finstaAdjustmentDate = dateFormatyyyyMMdd.format(dateAdjustment.getTime());

        // If cremul_standard payment is made without OCR line on purpose, it should use the other the payment file tredjemands_indbetaling  
        if (type == BetalingsfilType.CREMUL_STANDARD && (ocrLine == "" || ocrLine == null))
        {
            type = BetalingsfilType.CREMUL_UDEN_OCR_ELLER_TREDJEMANDS_BETALING;
        }
        switch (type) {
        case CREMUL_STANDARD:
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+170215:0700+151014U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+CREMUL:D:96A:UN'" + newline +
                    "BGM+X1+2017030114070055687271'" + newline +
                    "DTM+137:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "LIN+1'" + newline +
                    "DTM+202:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO++IBK'" + newline +
                    "MOA+60:" + paymentAmount + ":DKK'" + newline +
                    "RFF+KRE:81945403'" + newline +
                    "RFF+ACK:1495607717'" + newline +
                    "FII+BF+" + accountNo + "'" + newline +
                    "SEQ++1'" + newline +
                    "FII+BF'" + newline +
                    "RFF+CR:" + adviseringsTekst + "'" + newline +
                    "RFF+BID:" + ocrLine + "'" + newline +
                    "RFF+KAK:71'" + newline +
                    "MOA+98:" + paymentAmount + ":DKK'" + newline +
                    "UNT+18+1'" + newline +
                    "UNZ+1+151014U" + paymentFileID + "'";
        case CREMUL_INTERNATIONAL:
            // International payments are not implemented in PSRM, and will thus
            // make an assignment about payment customer couldn't be recognized
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+170215:0700+151014U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+CREMUL:D:96A:UN'" + newline +
                    "BGM+X1+2017030114070055687271'" + newline +
                    "DTM+137:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "LIN+1'" + newline +
                    "DTM+202:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++IN++UBB'" + newline +
                    "MOA+60:" + paymentAmount + ":DKK'" + newline +
                    "RFF+ACK:3825-0041915568'" + newline +
                    "FII+BF+" + accountNo + "+::::::RAIFFEISEN BANK POLSKA S.A.'" + newline +
                    "SEQ++1'" + newline +
                    "FII+BF'" + newline +
                    "FII+I1++::::::RAIFFEISEN BANK POLSKA S.A.        PIEKNA 20:           WARSZAWA'" + newline +
                    "RFF+CR:Betal. 3825-0041915568'" + newline +
                    "MOA+98:" + paymentAmount + ":DKK'" + newline +
                    "MOA+143:" + paymentAmount + ":DKK'" + newline +
                    "NAD+BE+FT15299801580315:KUN+/DK610216" + accountNo
                    + ":SKAT BETALING OSTBANEGADE 123 2100:COPENHAGEN'" + newline +
                    "NAD+OY+++JAN VILHELM:STJERNEBJERG:SOGADE 3D:4180 SORO'" + newline +
                    "FCA+15'" + newline +
                    "PRC+11'" + newline +
                    "FTX+PMD+++2304550433 JAN VILHELM STJERNEBJERG: BETALING IFOLGE AFTALE. AFD" + newline +
                    "RAG FOR: NOVEMBER'" + newline +
                    "GIS+37'" + newline +
                    "UNT+23+1'" + newline +
                    "UNZ+1+150311U" + paymentFileID + "'";
        case CREMUL_DAEKNINGSLOES:
            // Will make an assignment about the tester needs to locate the
            // original payment that needs to be 'daekningsloes'
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+170215:0700+151014U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+CREMUL:D:96A:UN'" + newline +
                    "BGM+X1+20161209070006299517'" + newline +
                    "DTM+137:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "LIN+1'" + newline +
                    "DTM+202:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO++CON'" + newline +
                    "MOA+60:" + paymentAmount + ":DKK'" + newline +
                    "RFF+ACK:" + ocrLine + "'" + newline +
                    "FII+BF+" + accountNo + "'" + newline +
                    "SEQ++1'" + newline +
                    "FII+OR'" + newline +
                    "FII+BF'" + newline +
                    "RFF+CR:Aut tomn'" + newline +
                    "MOA+98:" + paymentAmount + ":DKK'" + newline +
                    "UNT+16+1'" + newline +
                    "UNZ+1+150311U" + paymentFileID + "'";
        case CREMUL_UDEN_OCR_ELLER_TREDJEMANDS_BETALING:
            return "UNA:+,? '" + newline + 
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+151012:0010+151020U" + paymentFileID + "+++++248348'" + newline + 
                    "UNH+434315+CREMUL:D:96A:UN'" + newline + 
                    "BGM+X1+20151012001000756164'" + newline + 
                    "DTM+137:" + paymentDateyyyyMMdd + ":102'" + newline + 
                    "LIN+1'" + newline + 
                    "DTM+202:" + paymentDateyyyyMMdd + ":102'" + newline + 
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline + 
                    "BUS+DO+IBB'" + newline + 
                    "MOA+60:" + paymentAmount + ":DKK'" + newline + 
                    "RFF+KRE:86926709'" + newline + 
                    "RFF+ACK:667701999055'" + newline + 
                    "FII+BF+4069208401'" + newline + 
                    "SEQ++1'" + newline + 
                    "FII+BF'" + newline + 
                    "RFF+CR:FOCUS ADVOKATER P/S'" + newline + 
                    "FTX+PMD+++CHPO test'" + newline + 
                    "NAD+PL++Jokum Andersen+Søndergade 7+Sønderborg+6400'" + newline + 
                    "MOA+98:" + paymentAmount + ":DKK'" + newline + 
                    "UNT+15+1'" + newline + 
                    "UNZ+1+151020U" + paymentFileID + "'";
        case FINSTA_SKB:
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+161208:2313+161208U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+FINSTA:D:96A:UN'" + newline +
                    "BGM+KBG+553014+9'" + newline +
                    "DTM+137:" + finstaAdjustmentDate + ":102'" + newline +
                    "FII+MS++:::3237:80:130:Institutional Clients DK:Holmens Kanal 2, 1092 København+DK'" + newline +
                    "NAD+AC+++SKAT:ØSTBANEGADE 123:2100 KØBENHAVN Ø'" + newline +
                    "LIN+1'" + newline +
                    "FII+AS+" + accountNo + ":SKAT::DKK+:::0216:80:130'" + newline +
                    "RFF+NPS:823'" + newline +
                    "MOA+312::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "MOA+314::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "SEQ+15+000001'" + newline +
                    "RFF+ING:INGEN KODE, I STEDET ER FTX UDFYLDT'" + newline +
                    "MOA+323:" + paymentAmount + ":DKK'" + newline +
                    "DTM+179:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO++000'" + newline +
                    "FTX+BII+++Indbetalinger " + paymentAmount + "'" + newline +
                    "CNT+LIN:1'" + newline +
                    "CNT+SEQ:1'" + newline +
                    "UNT+41+1'" + newline +
                    "UNZ+1+161208U" + paymentFileID + "'";
        case FINSTA_NETS:
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+161208:2313+161208U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+FINSTA:D:96A:UN'" + newline +
                    "BGM+KBG+553014+9'" + newline +
                    "DTM+137:" + finstaAdjustmentDate + ":102'" + newline +
                    "FII+MS++:::3237:80:130:Institutional Clients DK:Holmens Kanal 2, 1092 København+DK'" + newline +
                    "NAD+AC+++SKAT:ØSTBANEGADE 123:2100 KØBENHAVN Ø'" + newline +
                    "LIN+1'" + newline +
                    "FII+AS+" + accountNo + ":SKAT::DKK+:::0216:80:130'" + newline +
                    "RFF+NPS:823'" + newline +
                    "MOA+312::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "MOA+314::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "SEQ+15+000001'" + newline +
                    "RFF+ING:INGEN KODE, I STEDET ER FTX UDFYLDT'" + newline +
                    "MOA+323:" + paymentAmount + ":DKK'" + newline +
                    "DTM+179:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO++000'" + newline +
                    "FTX+BII+++Indbetalinger " + paymentAmount + "'" + newline +
                    "CNT+LIN:1'" + newline +
                    "CNT+SEQ:1'" + newline +
                    "UNT+41+1'" + newline +
                    "UNZ+1+161208U" + paymentFileID + "'";
        case FINSTA_DAEKNINGSLOES:
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+161208:2313+161208U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+FINSTA:D:96A:UN'" + newline +
                    "BGM+KBG+553014+9'" + newline +
                    "DTM+137:" + finstaAdjustmentDate + ":102'" + newline +
                    "FII+MS++:::3237:80:130:Institutional Clients DK:Holmens Kanal 2, 1092 København+DK'" + newline +
                    "NAD+AC+++SKAT:ØSTBANEGADE 123:2100  KØBENHAVN Ø'" + newline +
                    "LIN+1'" + newline +
                    "FII+AS+4069208398:SKAT::DKK+:::0216:80:130'" + newline +
                    "RFF+NPS:823'" + newline +
                    "MOA+312::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "MOA+314::DKK'" + newline +
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "SEQ+13+000001'" + newline +
                    "RFF+ING:INGEN KODE, I STEDET ER FTX UDFYLDT'" + newline +
                    "DTM+179:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO+1+356'" + newline +
                    "MOA+323:-" + paymentAmount + ":DKK'" + newline +
                    "FTX+BII+++Overførsel'" + newline +
                    "SEQ+XAD+000002'" + newline +
                    "RFF+KUN'" + newline +
                    "DTM+209'" + newline +
                    "BUS++DO'" + newline +
                    "MOA+323'" + newline +
                    "CNT+SEQ:3'" + newline +
                    "UNT+41+1'" + newline +
                    "UNZ+1+161208U" + paymentFileID + "'";
        case FINSTA_DEBMUL:
            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+161208:2313+161208U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+FINSTA:D:96A:UN'" + newline +
                    "BGM+KBG+553014+9'" + newline +
                    "DTM+137:" + finstaAdjustmentDate + ":102'" + newline +
                    "FII+MS++:::3237:80:130:Institutional Clients DK:Holmens Kanal 2, 1092 København+DK'" + newline +
                    "NAD+AC+++SKAT:ØSTBANEGADE 123:2100 KØBENHAVN Ø'" + newline +
                    "LIN+1'" + newline +
                    "FII+AS+" + accountNo + "'" + newline +
                    "RFF+NPS:823'" + newline +
                    "MOA+312::DKK'" + newline + // Dont know why the original
                                                // paymentfile has this
                                                // hardcoded, but it works
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "MOA+314::DKK'" + newline + // Dont know why the original
                                                // paymentfile has this
                                                // hardcoded, but it works
                    "DTM+171:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "SEQ+15+000001'" + newline +
                    "RFF+ACK:6609044557'" + newline +
                    "MOA+323:-" + paymentAmount + ":DKK'" + newline +
                    "DTM+179:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO++000'" + newline +
                    "FTX+BII+++" + justeringsID + "'" + newline +
                    "CNT+LIN:1'" + newline +
                    "CNT+SEQ:1'" + newline +
                    "UNT+41+1'" + newline +
                    "UNZ+1+161208U" + paymentFileID + "'";
        case FINSTA_KUB:

            setPropertyProvider(new PropertyProviderImpl(this));
            doLogin();
            doMaximizeWindow();
            // get Psrm Date
            String[] psrmDateTime = MO_Utilities.getPsrmDateTime(this);
            String psrmDate = psrmDateTime[0];
            doLogout(); // might cause a problem, test

            // reformat the date format
            String day = psrmDate.substring(0, 2);
            String month = psrmDate.substring(3, 5);
            String year = psrmDate.substring(6, 10);
            psrmDate = year + month + day;

            return "UNA:+,? '" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+161208:2313+161208U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+FINSTA:D:96A:UN'" + newline +
                    "BGM+KBG+553014+9'" + newline +
                    "DTM+137:" + finstaAdjustmentDate + ":102'" + newline +
                    "FII+MS++:::3237:80:130:Institutional Clients DK:Holmens Kanal 2, 1092 København+DK'" + newline +
                    "NAD+AC+++SKAT:ØSTBANEGADE 123:2100 KØBENHAVN Ø'" + newline +
                    "LIN+1'" + newline +
                    "FII+AS+4069208428:SKAT::DKK+:::0216:80:130'" + newline +
                    "RFF+NPS:823'" + newline +
                    "MOA+312::DKK'" + newline +
                    "DTM+171:" + psrmDate + ":102'" + newline +
                    "MOA+314::DKK'" + newline +
                    "DTM+171:" + psrmDate + ":102'" + newline +
                    "SEQ+15+000001'" + newline +
                    "RFF+ING:INGEN KODE, I STEDET ER FTX UDFYLDT'" + newline +
                    "DTM+179:" + psrmDate + ":102'" + newline +
                    "DTM+209:" + psrmDate + ":102'" + newline +
                    "BUS++DO+1+000'" + newline +
                    "RFF+ACK:5512861608'" + newline +
                    "MOA+323:-" + paymentAmount + ":DKK'" + newline +
                    "FTX+BII+++" + justeringsID + "'" + newline +
                    "CNT+LIN:1'" + newline +
                    "CNT+SEQ:1'" + newline +
                    "UNT+41+1'" + newline +
                    "UNZ+1+161208U" + paymentFileID + "'";
        case M602_BETALINGSSERVICE:
            return generateM602File(betaling, newline, type);
        case M602_REJECTED_BETALINGSSERVICE:
            return generateM602File(betaling, newline, type);
        case M602_CANCELLED_BETALINGSSERVICE:
            return generateM602File(betaling, newline, type);
        case M602_TILBGAEFOERT_BETALING_FRA_BETALINGSSERVICE:
            return generateM602File(betaling, newline, type);
        case M602_COMPLETED_PAYMENTS_MADE_BY_INDBETALINGSKORT:
            return generateM602File(betaling, newline, type);
        case M602_TILBAGEFOERT_BETALING_FRA_INDBETALINGSKORT:
            return generateM602File(betaling, newline, type);
        case DEBMUL_STANDARD:
            return "UNA:+,?'" + newline +
                    "UNB+UNOC:2+5790000243440:14+5798000016446:14+151008:0701+151008U" + paymentFileID + "+++++248348'"
                    + newline +
                    "UNH+1+DEBMUL:D:96A:UN'" + newline +
                    "BGM+470+20170131071404218971'" + newline +
                    "DTM+137:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "LIN+1'" + newline +
                    "DTM+202:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "DTM+209:" + paymentDateyyyyMMdd + ":102'" + newline +
                    "BUS++DO'" + newline +
                    "MOA+60:" + paymentAmount + ":DKK'" + newline +
                    "RFF+ACK:6862607529'" + newline +
                    "RFF+CR:DBT." + justeringsID + "'" + newline +
                    "FII+OR+" + accountNo + "'" + newline +
                    "SEQ++1'" + newline +
                    "FII+BF+" + accountNo + ":::DKK'" + newline +
                    "PAI+::IBB:::UKA'" + newline +
                    "MOA+60:" + paymentAmount + ":DKK'" + newline +
                    "UNT+16+1'" + newline +
                    "UNZ+1+151008U" + paymentFileID + "'";
        default:
            return "";
        }
    }

    private File createFile(String fileName, String fileContent) throws IOException {
        // Filepath
        String filePath = indbetalingsfilSti;
        String prefix = (filePath.charAt(filePath.length() - 1) == '\\') ? "" : "\\";
        File newTextFile = new File(filePath + prefix + fileName);

        FileWriter fw = new FileWriter(newTextFile);
        fw.write(fileContent);
        fw.close();

        return newTextFile;
    }

    private String dynamicLeadingZero(Double amount, int NumOfzeroes) {
        String dynamicZeroes = "";
        for (int i = 0; i < NumOfzeroes; i++) {
            dynamicZeroes += "0";
        }

        DecimalFormat leadingZeroFormat = new DecimalFormat(dynamicZeroes + ".00");
        String str = leadingZeroFormat.format(amount).toString();

        str = str.replaceAll(",", "");
        str = str.replaceAll("\\.", "");
        return str;
    }

    private String generateM602File(Betaling betaling, String newline, BetalingsfilType type) {
        SimpleDateFormat dateFormatddMMyy = new SimpleDateFormat("ddMMyy");
        Date paymentDate = betaling.getDato();
        String paymentDateddMMyy = dateFormatddMMyy.format(paymentDate);
        Double paymentAmountDouble = betaling.getBeloebIDkk();
        String ocrLine = betaling.getOcr();

        String transactionCode = type.transactionCode();
        String cvr = "10200113"; // CVR nr.
        String pbs = "08747156"; // PBS nr.
        String debitor = "00001"; // Debitor group
        String creditorIdent = "10200113       "; // Creditor ident
        String mandate = "710002302"; // mandate
        int noOfPayments = 1;
        Double totalAmount = paymentAmountDouble; // sum of all payment Amounts
                                                  // (but the original solution
                                                  // was only designed to handle
                                                  // one
        String fileDate = paymentDateddMMyy; // File date
        String paymentDateM602 = paymentDateddMMyy; // Payment date
        String actualDate = paymentDateddMMyy; // Actual date
        String transactionDate = paymentDateddMMyy; // bogført date

        String M5 = transactionCode;
        String $G$5 = cvr; // CVR nr.
        String $I$5 = Etc.generateNDigitNumberAsString(10); // dateFormatyyMMddhhss.format(System.currentTimeMillis());
                                                            // //Serial
                                                            // yyMMddttss (this
                                                            // was tested out
                                                            // not to be stable
                                                            // enough to
                                                            // generate
                                                            // uniqueId)
        String $H$5 = pbs; // PBS nr.
        String $J$5 = debitor; // Debitor group
        String $K$5 = creditorIdent; // Creditor ident
        String $F$5 = fileDate; // File date
        String E8 = ocrLine;
        String F8 = paymentDateM602; // Payment date
        String G8 = actualDate; // Actual date
        String H8 = transactionDate; // bogført date
        String $L$5 = mandate; // mandate
        String paymentType;
        String $E$20 = String.format("%011d", noOfPayments); // NoOfPayments

        String D8 = dynamicLeadingZero(paymentAmountDouble, 11); // Beløb af
                                                                 // enkelt
                                                                 // indbetaling
        String $D$20 = dynamicLeadingZero(totalAmount, 13); // sum af alle
                                                            // indbetalinger
                                                            // //String
                                                            // totalPaymentAmount

        String paymentAmountTest = "0";
        if (paymentAmountDouble > 0) {
            paymentAmountTest = "1";
        }

        if (transactionCode == "0297" || transactionCode == "0299") {
            // if payment done with indbetalingskort, then use below format
            paymentType = "BS042" + $H$5 + M5 + "000" + $J$5 + "0000" + E8 + "71000000" + F8 + paymentAmountTest + D8
                    + "999999999RIM: " + E8 + "  " + G8 + H8 + D8;
        } else {
            // if payment done with betalingsservice, then use below format
            paymentType = "BS042" + $H$5 + M5 + "000" + $J$5 + E8 + $L$5 + F8 + paymentAmountTest + D8 + "RIM: " + E8
                    + "              " + G8 + H8 + D8;
        }

        return "BS002" + $G$5 + "BS40602" + $I$5 + "                   " + $F$5
                + "                                                                         " + newline +
                "BS012" + $H$5 + "0211000" + $J$5 + $K$5 + "         " + $F$5
                + "                                                                         " + newline +
                newline +
                paymentType + newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                newline +
                "BS092" + $H$5 + "0211" + "000" + $J$5 + "      " + $E$20 + $D$20 + "00000000000" + "               "
                + "00000000000" + "                                  " + newline +
                "BS992" + $G$5 + "BS40602" + "00000000001" + $E$20 + $D$20 + "00000000000" + "000000000000000"
                + "00000000000" + $F$5 + "0000000000000000000000000000000000";// '));";
    }

}
