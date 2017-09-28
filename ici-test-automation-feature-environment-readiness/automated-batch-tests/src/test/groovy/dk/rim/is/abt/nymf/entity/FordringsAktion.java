package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.converters.AfvistAarsagSamlingAttributeConverter;
import dk.rim.is.api.converters.MFHoeringStatusStrukturAttributeConverter;
import dk.rim.is.api.converters.PsrmResponseAttributeConverter;
import dk.rim.is.api.converters.UdfoertAktionValgAttributeConverter;
import dk.rim.is.api.enums.FordringsRelation;
import dk.rim.is.api.helpers.IOUtils;
import dk.skat.begrebsmodel._2009._01._15.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.w3c.dom.Document;
import ws.binding.psrm.MFFordringIndberetO;
import ws.binding.psrm.MFHoeringStatusStrukturType;

import javax.persistence.*;
import javax.xml.bind.JAXB;
import javax.xml.transform.dom.DOMSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Aktion entity.
 * <p>
 * Created by frbm on 29-11-2016.
 */

@Entity
@Table(name = "FORDRINGS_AKTION")
public class FordringsAktion implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AKTION_GENERATOR")
    @SequenceGenerator(name = "AKTION_GENERATOR", sequenceName = "AKTION_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="LEVERANCE_ID", foreignKey = @ForeignKey(name = "FK_LEVERANCE"))
    private LeveranceEntity leveranceEntity;

    @Column
    private Long fordringsId;

    @Column
    private LocalDateTime requestTimestamp;

    @Column
    private LocalDateTime modtagelsesTidspunkt;

    @Column
    private Long hovedfordringId;

    @OneToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="FA_DATA_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_FA_DATA"))
    private FordringsAktionData fordringsAktionData;

    @Column
    private Long fordringshaverSystemId;

    @Column
    private Long fordringhaverId;

    @Column
    @Enumerated(EnumType.STRING)
    private MFAktionKodeType aktionKode;

    @Column
    private String fordringhaverReferance;

    @Column
    @Enumerated(EnumType.STRING)
    private DMIFordringArtType artType; // DMIFordringFordringArtKode

    @Column
    private String fordringTypeKode;

    @Column
    @Enumerated(EnumType.STRING)
    private FordringsRelation fordringsRelation;

    @Column
    private Long aktionId;

    @Column
    private Long aktionIdRef;

    @Lob
    @Fetch(FetchMode.SELECT)
    @Column(columnDefinition="clob")
    private String psrmResponse;

    @Transient
    private MFFordringIndberetO psrmResponseXMLObject;

    @Lob
    @Fetch(FetchMode.SELECT)
    @Column(columnDefinition="clob")
    private String afvistAarsagsSamling;

    @Transient
    private MFAktionStrukturType.AfvistÅrsagSamling afvistAarsagsSamlingXMLObject;

    @Column(precision = 13, scale = 2, nullable = true)
    private BigDecimal beloeb;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private FordringNedskrivningÅrsagKodeType fordringNedskrivningAarsagKode;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private HovedFordringTilbagekaldÅrsagKodeDomæneType hovedFordringTilbageAarsagKode;

    public BigDecimal getBeloeb() {
        return beloeb;
    }

    @Lob
    @Column(columnDefinition="clob")
    private String udfoertAktionValg;

    @Transient
    private MFKvitteringHentOType.KvitteringSamling.Kvittering.UdførtAktionValg udfoertAktionValgXMLObject;

    @OneToOne(optional=false, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="FA_STATE_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_FA_STATE"))
    private FordringsAktionState fordringsAktionState;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="FORDRINGS_AKTION_ID", referencedColumnName="ID", foreignKey = @ForeignKey(name = "FK_FORDRINGS_AKTION"))
    private List<Skyldner> skyldnere  = new ArrayList<>();

    @Transient
    private MFFordringIndberetI.FordringAktionSamling.FordringAktion fordringAktion;

    @Transient
    private MFOpretRelateretFordringStrukturType mfOpretFordringStruktur;

    @Lob
    @Fetch(FetchMode.SELECT)
    @Column(columnDefinition="clob")
    private String mfHoeringStatusStrukturType;

    @Transient
    private MFHoeringStatusStrukturType mfHoeringStatusStrukturTypeXMLObject;

    @Column
    @Convert(converter = LocalDateConverter.class)
    private LocalDate virkningDato;

    @Column(nullable = true)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate korrigeretVirkningDato;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeveranceId() {
        return leveranceEntity.getLeveranceId();
    }

    public void setLeveranceId(Long leveranceId) {
        this.leveranceEntity.setLeveranceId(leveranceId);
    }

    public LeveranceEntity getLeveranceEntity() {
        return leveranceEntity;
    }

    public void setLeveranceEntity(LeveranceEntity leveranceEntity) {
        this.leveranceEntity = leveranceEntity;
    }

    public void addLeveranceEntity(LeveranceEntity leveranceEntity) {
        this.setLeveranceEntity(leveranceEntity);
        leveranceEntity.getFordringsAktioner().add(this);
    }
    public Long getFordringsId() {
        return fordringsId;
    }

    public void setFordringsId(Long fordringsId) {
        this.fordringsId = fordringsId;
    }

    public FordringsAktionData getFordringsAktionData() {
        return fordringsAktionData;
    }

    public void setFordringsAktionData(FordringsAktionData fordringsAktionData) {
        this.fordringsAktionData = fordringsAktionData;
    }

    public String getData() {
        return fordringsAktionData.getData();
    }

    public void setData(String data) {
        if(fordringsAktionData == null) {
            fordringsAktionData = new FordringsAktionData();
        }
        fordringsAktionData.setData(data);
    }

    public LocalDateTime getRequestTimestamp() { return requestTimestamp; }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public LocalDateTime getModtagelsesTidspunkt() {
        return modtagelsesTidspunkt;
    }

    public LocalDate getModtagelsesTidspunktAsLocalDate() {
        return modtagelsesTidspunkt == null ? null : modtagelsesTidspunkt.toLocalDate();
    }

    public void setModtagelsesTidspunkt(LocalDateTime modtagelsesTidspunkt) {
        this.modtagelsesTidspunkt = modtagelsesTidspunkt;
    }

    public Long getHovedfordringId() {
        return hovedfordringId;
    }

    public void setHovedfordringId(Long hovedfordringId) {
        this.hovedfordringId = hovedfordringId;
    }

    public Long getFordringshaverSystemId() {
        return fordringshaverSystemId;
    }

    public void setFordringshaverSystemId(Long fordringshaverSystemId) {
        this.fordringshaverSystemId = fordringshaverSystemId;
    }

    public Long getFordringhaverId() {
        return fordringhaverId;
    }

    public void setFordringhaverId(Long fordringhaverId) {
        this.fordringhaverId = fordringhaverId;
    }

    public MFAktionKodeType getAktionKode() {
        return aktionKode;
    }

    public void setAktionKode(MFAktionKodeType aktionKode) {
        this.aktionKode = aktionKode;
    }

    public void setFordringhaverReferance(String fordringhaverReferance) {
        this.fordringhaverReferance = fordringhaverReferance;
    }

    public String getFordringhaverReferance() {
        return fordringhaverReferance;
    }

    public DMIFordringArtType getArtType() {
        return artType;
    }

    public void setArtType(DMIFordringArtType artType) {
        this.artType = artType;
    }

    public String getFordringTypeKode() {
        return fordringTypeKode;
    }

    public void setFordringTypeKode(String fordringTypeKode) {
        this.fordringTypeKode = fordringTypeKode;
    }

    public String getPsrmResponse() {
        return psrmResponse;
    }

    public void setPsrmResponse(String psrmResponse) {
        this.psrmResponse = psrmResponse;
    }

    public MFFordringIndberetO getPsrmResponseXMLObject() {
        if(psrmResponseXMLObject == null) {
            psrmResponseXMLObject = PsrmResponseAttributeConverter.convertToEntityAttribute(psrmResponse);
        }
        return psrmResponseXMLObject;
    }

    public void setPsrmResponseXMLObject(MFFordringIndberetO psrmResponseXMLObject) {
        this.psrmResponseXMLObject = psrmResponseXMLObject;
        this.psrmResponse = PsrmResponseAttributeConverter.convertToDatabaseColumn(psrmResponseXMLObject);
    }

    public MFAktionStrukturType.AfvistÅrsagSamling getAfvistAarsagsSamlingXMLObject() {
        if(afvistAarsagsSamlingXMLObject == null) {
            afvistAarsagsSamlingXMLObject = AfvistAarsagSamlingAttributeConverter.convertToEntityAttribute(afvistAarsagsSamling);
        }
        return afvistAarsagsSamlingXMLObject;
    }

    public void setAfvistAarsagsSamlingXMLObject(MFAktionStrukturType.AfvistÅrsagSamling afvistAarsagsSamlingXMLObject) {
        this.afvistAarsagsSamlingXMLObject = afvistAarsagsSamlingXMLObject;
        this.afvistAarsagsSamling = AfvistAarsagSamlingAttributeConverter.convertToDatabaseColumn(afvistAarsagsSamlingXMLObject);

    }

    public String getAfvistAarsagsSamling() {
        return afvistAarsagsSamling;
    }

    public void setAfvistAarsagsSamling(String afvistAarsagsSamling) {
        this.afvistAarsagsSamling = afvistAarsagsSamling;
    }

    public FordringsAktionState getFordringsAktionState() {
        return fordringsAktionState;
    }

    public void setFordringsAktionState(FordringsAktionState fordringsAktionState) {
        this.fordringsAktionState = fordringsAktionState;
    }

    public MFFordringIndberetI.FordringAktionSamling.FordringAktion getFordringAktion() {
        if ((fordringAktion != null)) {
            return fordringAktion;
        }

        try {
            if (elementExists("FordringAktion")) {
                Document document = IOUtils.getXMLNodeFromString(fordringsAktionData.getData());
                fordringAktion = JAXB.unmarshal(new DOMSource(document), MFFordringIndberetIType.FordringAktionSamling.FordringAktion.class);
            }
        } catch (Exception ignored) {}

        return fordringAktion;
    }


    public MFOpretRelateretFordringStrukturType getMFOpretRelateretFordringStruktur() {
        if ((mfOpretFordringStruktur != null)) {
            return mfOpretFordringStruktur;
        }

        try {
            if (!elementExists("FordringAktion")) {
                Document document = IOUtils.getXMLNodeFromString(fordringsAktionData.getData());
                mfOpretFordringStruktur = JAXB.unmarshal(new DOMSource(document), MFOpretRelateretFordringStrukturType.class);
            }
        } catch (Exception ignored) {}

        return mfOpretFordringStruktur;
    }

    public <T> T getMFOpretRelateretFordringStruktur(Class<T> requestedType) {
        try {
            if (!elementExists("FordringAktion")) {
                Document document = IOUtils.getXMLNodeFromString(fordringsAktionData.getData());
                return JAXB.unmarshal(new DOMSource(document), requestedType);
            }
        } catch (Exception ignored) {}

        return null;
    }

    /**
     * use this for both HF and UF.
     * @return
     */
    public List<MFOpretFordringStrukturType> getOpretFordringFlat() {
        MFFordringIndberetI.FordringAktionSamling.FordringAktion getFordringAktion = getFordringAktion();
        if(getFordringAktion != null){
            List<MFOpretFordringStrukturType> list = new ArrayList<>();
            list.add(getFordringAktion.getAktionValg().getOpretFordringAktion().getMFOpretFordringStruktur());
            list.addAll(getFordringAktion.getAktionValg().getOpretFordringAktion().getOpretUnderfordringSamling().getMFOpretRelateretFordringStruktur());
            return list;
        } else if(getMFOpretRelateretFordringStruktur() != null &&
                getMFOpretRelateretFordringStruktur().getOpretUnderfordringSamling() != null &&
                getMFOpretRelateretFordringStruktur().getOpretUnderfordringSamling().getMFOpretRelateretFordringStruktur() != null){
            return new ArrayList<>(getMFOpretRelateretFordringStruktur().getOpretUnderfordringSamling().getMFOpretRelateretFordringStruktur());
        } else{
            return new ArrayList<>();
        }
    }

    public FordringsRelation getFordringsRelation() {
        return fordringsRelation;
    }

    public void setFordringsRelation(FordringsRelation fordringsRelation) {
        this.fordringsRelation = fordringsRelation;
    }

    public Long getAktionId() {
        return aktionId;
    }

    public void setAktionId(Long aktionId) {
        this.aktionId = aktionId;
    }

    public Long getAktionIdRef() {
        return aktionIdRef;
    }

    public void setAktionIdRef(Long aktionIdRef) {
        this.aktionIdRef = aktionIdRef;
    }

    private boolean elementExists(String tagName) {
        if (fordringsAktionData != null && fordringsAktionData.getData() != null) {
            if (fordringsAktionData.getData().contains(tagName + ">")) {
                return true;
            }
        }
        return false;
    }

    public MFKvitteringHentOType.KvitteringSamling.Kvittering.UdførtAktionValg getUdfoertAktionValgXMLObject() {
        if(udfoertAktionValgXMLObject == null) {
            udfoertAktionValgXMLObject = UdfoertAktionValgAttributeConverter.convertToEntityAttribute(udfoertAktionValg);
        }
        return udfoertAktionValgXMLObject;
    }

    public void setUdfoertAktionValgXMLObject(MFKvitteringHentOType.KvitteringSamling.Kvittering.UdførtAktionValg exmfUdfoertAktionValgXMLObject) {
        this.udfoertAktionValgXMLObject = exmfUdfoertAktionValgXMLObject;
        this.udfoertAktionValg = UdfoertAktionValgAttributeConverter.convertToDatabaseColumn(exmfUdfoertAktionValgXMLObject);
    }

    public String getUdfoertAktionValg() {
        return udfoertAktionValg;
    }

    public void setUdfoertAktionValgXMLObject(String exmfUdfoertAktionValg) {
        this.udfoertAktionValg = exmfUdfoertAktionValg;
    }

    public void addToSkyldnere(List<Skyldner> skyldners) {
        skyldnere.addAll(skyldners);
    }

    public List<Skyldner> getSkyldnere() {
        return skyldnere;
    }

    public void setSkyldnere(List<Skyldner> skyldnere) {
        this.skyldnere = skyldnere;
    }

    public void removeLeveranceEntity() {
        this.getLeveranceEntity().getFordringsAktioner().remove(this);
        this.setLeveranceEntity(null);
    }

    public void setMFHoeringStatusStrukturXMLObject(MFHoeringStatusStrukturType mfHoeringStatusStrukturTypeXMLObject) {
        this.mfHoeringStatusStrukturTypeXMLObject = mfHoeringStatusStrukturTypeXMLObject;
        this.mfHoeringStatusStrukturType = MFHoeringStatusStrukturAttributeConverter.convertToDatabaseColumn(mfHoeringStatusStrukturTypeXMLObject);
    }

    public MFHoeringStatusStrukturType getMfHoeringStatusStrukturTypeXMLObject() {
        if(mfHoeringStatusStrukturTypeXMLObject == null) {
            mfHoeringStatusStrukturTypeXMLObject = MFHoeringStatusStrukturAttributeConverter.convertToEntityAttribute(mfHoeringStatusStrukturType);
        }
        return mfHoeringStatusStrukturTypeXMLObject;
    }

    public void setMFHoeringStatusStruktur(String mfhoeringstatusstrukturType) {
        this.mfHoeringStatusStrukturType = mfhoeringstatusstrukturType;
    }

    public String getMfHoeringStatusStrukturType() {
        return mfHoeringStatusStrukturType;
    }

    public void setBeloeb(BigDecimal fordringBeloeb) {
        this.beloeb = fordringBeloeb;
    }

    public FordringNedskrivningÅrsagKodeType getFordringNedskrivningAarsagKode() {
        return fordringNedskrivningAarsagKode;
    }

    public void setFordringNedskrivningAarsagKode(FordringNedskrivningÅrsagKodeType fordringNedskrivningAarsagKode) {
        this.fordringNedskrivningAarsagKode = fordringNedskrivningAarsagKode;
    }

    public HovedFordringTilbagekaldÅrsagKodeDomæneType getHovedFordringTilbageAarsagKode() {
        return hovedFordringTilbageAarsagKode;
    }

    public void setHovedFordringTilbageAarsagKode(HovedFordringTilbagekaldÅrsagKodeDomæneType hovedFordringTilbageAarsagKode) {
        this.hovedFordringTilbageAarsagKode = hovedFordringTilbageAarsagKode;
    }

    @Override
    public String toString() {
        return "FordringsAktion{" + "id=" + id + ", leveranceEntity=" + leveranceEntity.getId() + ", fordringsId=" + fordringsId + ", requestTimestamp=" + requestTimestamp + ", modtagelsesTidspunkt=" + modtagelsesTidspunkt + ", hovedfordringId=" + hovedfordringId + ", fordringshaverSystemId=" + fordringshaverSystemId + ", fordringhaverId=" + fordringhaverId + ", aktionKode=" + aktionKode + ", fordringhaverReferance='" + fordringhaverReferance + '\'' + ", artType=" + artType + ", fordringsType=" + fordringsRelation + ", aktionId=" + aktionId + ", fordringsAktionState=" + fordringsAktionState + '}';
    }

    public boolean areAnyFieldsRequiredForAfstemningsrapporterNull() {
        for(Boolean entry : getFieldsRequredForAfstemningsrapporterThatAreNullInt().values()){
            if(!entry){
                return false;
            }
        }
        return true;
    }

    public Set<String> getFieldsRequiredForAfstemningsrapporterThatAreNull() {
        return getFieldsRequredForAfstemningsrapporterThatAreNullInt().keySet();
    }

    private Map<String, Boolean> getFieldsRequredForAfstemningsrapporterThatAreNullInt() {
        Map<String, Boolean> fieldsToValue = new HashMap<>();
        if(aktionKode==MFAktionKodeType.OPSKRIVNINGREGULERING ||

                aktionKode==MFAktionKodeType.OPSKRIVNINGANNULLERETNEDSKRIVNINGINDBETALING  ){
            fieldsToValue.put("beloeb", this.beloeb == null);
        } else if(aktionKode==MFAktionKodeType.TILBAGEKALD){
            fieldsToValue.put("hovedFordringTilbageAarsagKode", this.hovedFordringTilbageAarsagKode == null);
        } else if(aktionKode==MFAktionKodeType.NEDSKRIV){
            fieldsToValue.put("fordringNedskrivningAarsagKode", this.fordringNedskrivningAarsagKode == null);
        } else if(aktionKode==MFAktionKodeType.OPRETFORDRING || aktionKode==MFAktionKodeType.GENINDSENDFORDRING){
            fieldsToValue.put("beloeb", this.beloeb == null);
        }
        return fieldsToValue;
    }

    public LocalDate getVirkningDato() {
        return virkningDato;
    }

    public void setVirkningDato(LocalDate virkningDato) {
        this.virkningDato = virkningDato;
    }

    public LocalDate getKorrigeretVirkningDato() {
        return this.korrigeretVirkningDato;
    }

    public void setKorrigeretVirkningDato(LocalDate korrigeretVrkningDato) {
        this.korrigeretVirkningDato = korrigeretVrkningDato;
    }
    public BigDecimal getEFIFordringOprindeligBeloeb() {
        return this.getFordringAktion().getAktionValg().getOpretFordringAktion().getMFOpretFordringStruktur().getFordringOprindeligBeløbStruktur().getEFIFordringOprindeligBeløb();
    }
}
