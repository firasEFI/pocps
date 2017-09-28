package utils.batchJob;

public enum BatchJobType {
    /**
     * <STRONG>Batchjob Description</STRONG>: AogDRequestStatus
     */
    AogDRequestStatus("AogDRequestStatus", "AogDRequestStatus"),
    /**
     * <STRONG>Batchjob Description</STRONG>: AogDSendLetters
     */
    AogDSendLetters("AogDSendLetters", "AogDSendLetters"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Process C1-PEPL1 - Upload Payments
     */
    C1_PEPL1("C1-PEPL1", "Process C1-PEPL1 - Upload Payments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Process C1-PEPL2 - Upload Payments
     */
    C1_PEPL2("C1-PEPL2", "Process C1-PEPL2 - Upload Payments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Process C1-PEPL3 - Upload Payments
     */
    C1_PEPL3("C1-PEPL3", "Process C1-PEPL3 - Upload Payments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Cancel Settlement Stop and Create
     * a to do
     */
    C1_PFLM_CancelSettlement("C1-PFLM", "Cancel Settlement Stop and Create a to do"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CloseDemandCase
     */
    C1_PFLM_CloseDemandCase("C1-PFLM", "CloseDemandCase"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CloseHearingCase
     */
    C1_PFLM_CloseHearingCase("C1-PFLM", "CloseHearingCase"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CreateInstallment,
     * MarkInstallmentAsPaid ClosePaymentPlanCase
     */
    C1_PFLM_CreateInstallment("C1-PFLM", "CreateInstallment, MarkInstallmentAsPaid ClosePaymentPlanCase"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Create to do when balance is zero
     * - Estate case
     */
    C1_PFLM_CreateToDoEstateCase("C1-PFLM", "Create to do when balance is zero - Estate case"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Create to do when balance is zero
     * - Foreign Country Case
     */
    C1_PFLM_CreateToDoForeignCountryCase("C1-PFLM", "Create to do when balance is zero - Foreign Country Case"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CreateToDoIf-RecommendationExpired
     */
    C1_PFLM_CreateToDoRecommendationExpired("C1-PFLM", "CreateToDoIf-RecommendationExpired"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Create to do when balance is zero
     * - Repossession Case
     */
    C1_PFLM_CreateToDoRepossesionCase("C1-PFLM", "Create to do when balance is zero - Repossession Case"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Create to do when balance is zero
     * - Suppression case
     */
    C1_PFLM_CreateToDoSuppressionCase("C1-PFLM", "Create to do when balance is zero - Suppression case"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Interest Suppression Monitor
     */
    C1_SUPPM("C1-SUPPM", "Interest Suppression Monitor"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Tax Form Deferred Monitor
     */
    C1_TXFRM("C1-TXFRM", "Tax Form Deferred Monitor"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVACCI
     */
    CIPVACCI("CIPVACCI", "CIPVACCI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVACCK
     */
    CIPVACCK("CIPVACCK", "CIPVACCK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVACPI
     */
    CIPVACPI("CIPVACPI", "CIPVACPI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVADJI
     */
    CIPVADJI("CIPVADJI", "CIPVADJI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVADJK
     */
    CIPVADJK("CIPVADJK", "CIPVADJK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVAILI
     */
    CIPVAILI("CIPVAILI", "CIPVAILI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVAINI
     */
    CIPVAINI("CIPVAINI", "CIPVAINI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVAINK
     */
    CIPVAINK("CIPVAINK", "CIPVAINK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVASTK
     */
    CIPVASTK("CIPVASTK", "CIPVASTK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVBILK
     */
    CIPVBILK("CIPVBILK", "CIPVBILK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVFTFI
     */
    CIPVFTFI("CIPVFTFI", "CIPVFTFI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVFTGI
     */
    CIPVFTGI("CIPVFTGI", "CIPVFTGI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVFTXK
     */
    CIPVFTXK("CIPVFTXK", "CIPVFTXK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPADI
     */
    CIPVPADI("CIPVPADI", "CIPVPADI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPERI
     */
    CIPVPERI("CIPVPERI", "CIPVPERI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPERK
     */
    CIPVPERK("CIPVPERK", "CIPVPERK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPIDI
     */
    CIPVPIDI("CIPVPIDI", "CIPVPIDI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPNMI
     */
    CIPVPNMI("CIPVPNMI", "CIPVPNMI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPRCI
     */
    CIPVPRCI("CIPVPRCI", "CIPVPRCI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVPRMK
     */
    CIPVPRMK("CIPVPRMK", "CIPVPRMK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSACI
     */
    CIPVSACI("CIPVSACI", "CIPVSACI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSUCI
     */
    CIPVSUCI("CIPVSUCI", "CIPVSUCI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSUEI
     */
    CIPVSUEI("CIPVSUEI", "CIPVSUEI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSULI
     */
    CIPVSULI("CIPVSULI", "CIPVSULI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSUMI
     */
    CIPVSUMI("CIPVSUMI", "CIPVSUMI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSUPI
     */
    CIPVSUPI("CIPVSUPI", "CIPVSUPI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSUPK
     */
    CIPVSUPK("CIPVSUPK", "CIPVSUPK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSURI
     */
    CIPVSURI("CIPVSURI", "CIPVSURI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSVAI
     */
    CIPVSVAI("CIPVSVAI", "CIPVSVAI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVSVAK
     */
    CIPVSVAK("CIPVSVAK", "CIPVSVAK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVTXRI
     */
    CIPVTXRI("CIPVTXRI", "CIPVTXRI"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CIPVTXRK
     */
    CIPVTXRK("CIPVTXRK", "CIPVTXRK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CsrpUpdatePerson
     */
    CsrpUpdatePersonDispatcher("CsrpUpdatePersonDispatcher", "CsrpUpdatePerson"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Calculate Interest - Monthly
     */
    DK_CALPI_MonthlyInterest("DK_CALPI", "Calculate Interest - Monthly"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Calculate Interest - Yearly
     */
    DK_CALPI_YearlyInterest("DK_CALPI", "Calculate Interest - Yearly"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Generate claimants notifications
     */
    DKCLNOTI("DKCLNOTI", "Generate claimants notifications"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NKSClaimantPayouts
     */
    DKCLOVP("DKCLOVP", "NKSClaimantPayouts"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NKSPayoutResponse
     */
    DKCLRSP("DKCLRSP", "NKSPayoutResponse"),
    /**
     * <STRONG>Batchjob Description</STRONG>: DKCRVCHR - Create Vouchers
     */
    DKCRVCHR("DKCRVCHR", "DKCRVCHR - Create Vouchers"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Create To Dos from DW table
     */
    DKCTDFT("DKCTDFT", "Create To Dos from DW table"),
    /**
     * <STRONG>Batchjob Description</STRONG>: DeactivateDebtor
     */
    DKDACP("DKDACP", "DeactivateDebtor"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NKSDebtorPayouts
     */
    DKDBOVP("DKDBOVP", "NKSDebtorPayouts"),
    /**
     * <STRONG>Batchjob Description</STRONG>: ObligationsReached LimitionDate
     */
    DKDGOCTD("DKDGOCTD", "ObligationsReached LimitionDate"),
    /**
     * <STRONG>Batchjob Description</STRONG>: ObligationsReachingLimitionDate
     */
    DKDROCTD("DKDROCTD", "ObligationsReachingLimitionDate"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NetsBills
     */
    DKNB("DKNB", "NetsBills"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NetsBillsResponse
     */
    DKNBR("DKNBR", "NetsBillsResponse"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NetsPayments
     */
    DKNP("DKNP", "NetsPayments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CreatePersonCasesInWorkZone
     */
    DKRSCFN_CreatePersonCase("DKRSCFN", "CreatePersonCasesInWorkZone"),
    /**
     * <STRONG>Batchjob Description</STRONG>: JournalizeNotesToWorkZone
     */
    DKRSCFN_JournalizeNotes("DKRSCFN", "JournalizeNotesToWorkZone"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SKBPayouts
     */
    DKSKBO("DKSKBO", "SKBPayouts"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SKBPayments
     */
    DKSKBP("DKSKBP", "SKBPayments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SKBTransactions
     */
    DKSKBT("DKSKBT", "SKBTransactions"),
    /**
     * <STRONG>Batchjob Description</STRONG>: CreateToDoFromCSVFile
     */
    DKTDCSV("DKTDCSV", "CreateToDoFromCSVFile"),
    /**
     * <STRONG>Batchjob Description</STRONG>: DKTRVCHR - Transfer Vouchers
     */
    DKTRVCHR("DKTRVCHR", "DKTRVCHR - Transfer Vouchers"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Tax Form Deferred Monitor - DK
     */
    DK_TXFRM_DeferredMonitor("DK-TXFRM", "Tax Form Deferred Monitor - DK"),
    /**
     * <STRONG>Batchjob Description</STRONG>: DK Tax Form Deferred Monitor
     * MODTAG
     */
    DK_TXFRM_DeferredMonitorModtag("DK-TXFRM", "DK Tax Form Deferred Monitor MODTAG"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Try calling NyMF again -
     */
    F1_FCTRN("F1-FCTRN", "Try calling NyMF again -"),
    /**
     * <STRONG>Batchjob Description</STRONG>: F1-FKVBP
     */
    F1_FKVBP("F1-FKVBP", "F1-FKVBP"),
    /**
     * <STRONG>Batchjob Description</STRONG>: GLASSIGN, assigning the GL account
     */
    GLASSIGN("GLASSIGN", "GLASSIGN, assigning the GL account"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NemKontoExportPayments
     */
    NemKontoExportPayments("NemKontoExportPayments", "NemKontoExportPayments"),
    /**
     * <STRONG>Batchjob Description</STRONG>: NemKontoReceiveReceipts
     */
    NemKontoReceiveReceipts("NemKontoReceiveReceipts", "NemKontoReceiveReceipts"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Nets601FileUpload
     */
    Nets601FileUpload("Nets601FileUpload", "Nets601FileUpload"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Retskraftsvurdering
     */
    Retskraftsvurdering("Retskraftsvurdering", "Retskraftsvurdering"),
    /**
     * <STRONG>Batchjob Description</STRONG>: Routing
     */
    Routing("Routing", "Routing"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SaveSentLetters
     */
    SaveSentLetters("SaveSentLetters", "SaveSentLetters"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SendToExMF
     */
    SendToExMF("SendToExMF", "SendToExMF"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SendToHearing
     */
    SendToHearing("SendToHearing", "SendToHearing"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SendToPSRM
     */
    SendToPSRM("SendToPSRM", "SendToPSRM"),
    /**
     * <STRONG>Batchjob Description</STRONG>: SkylderValidation
     */
    SkyldnerValidation("SkyldnerValidation", "SkylderValidation"),
    /**
     * <STRONG>Batchjob Description</STRONG>: VAL-ACCT
     */
    VAL_ACCT("VAL-ACCT", "VAL-ACCT"),
    /**
     * <STRONG>Batchjob Description</STRONG>: VAL-OBLG
     */
    VAL_OBLG("VAL-OBLG", "VAL-OBLG"),
    /**
     * <STRONG>Batchjob Description</STRONG>: VAL-PER
     */
    VAL_PER("VAL-PER", "VAL-PER"),
    /**
     * <STRONG>Batchjob Description</STRONG>: VAL-SUPP
     */
    VAL_SUPP("VAL-SUPP", "VAL-SUPP"),
    /**
     * <STRONG>Batchjob Description</STRONG>: VAL-TAXR
     */
    VAL_TAXR("VAL-TAXR", "VAL-TAXR");

    private String name;
    
    private String description;

    private BatchJobType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
