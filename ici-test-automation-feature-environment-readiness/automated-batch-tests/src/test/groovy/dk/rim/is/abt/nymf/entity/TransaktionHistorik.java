package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.enums.ServiceName;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSAKTION_HISTORIK")
public class TransaktionHistorik implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSAKTION_HIST_GENERATOR")
    @SequenceGenerator(name = "TRANSAKTION_HIST_GENERATOR", sequenceName = "TRANSAKTION_HIST_SEQ", allocationSize=1)
    @Column
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="transaktionHistorik", cascade = CascadeType.ALL, orphanRemoval = true)
    private Transaktion transaktion;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="transaktionHistorik", cascade = CascadeType.ALL)
    private dk.rim.is.abt.nymf.entity.FordringIndberetHistorik fordringIndberetHistorik;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="transaktionHistorik", cascade = CascadeType.ALL)
    private ModtagStatusOpdateringHistorik modtagStatusOpdateringHistorik;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="transaktionHistorik", cascade = CascadeType.ALL)
    private UnderretSamlingHentHistorik underretSamlingHentHistorik;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="transaktionHistorik", cascade = CascadeType.ALL)
    private KvitteringHentHistorik kvitteringHentHistorik;

    @Column
    private LocalDateTime timestamp;

    @Column
    @Enumerated(EnumType.STRING)
    private ServiceName requestType;

    @Column
    private String transaktionId;

    @Column
    private int errorCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransaktionId() {
        return transaktionId;
    }

    public void setTransaktionId(String transaktionId) {
        this.transaktionId = transaktionId;
    }

    public Transaktion getTransaktion() {
        return transaktion;
    }

    public void setTransaktion(Transaktion transaktion) {
        this.transaktion = transaktion;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public dk.rim.is.abt.nymf.entity.FordringIndberetHistorik getFordringIndberetHistorik() {
        return fordringIndberetHistorik;
    }

    public void setFordringIndberetHistorik(dk.rim.is.abt.nymf.entity.FordringIndberetHistorik fordringIndberetHistorik) {
        this.fordringIndberetHistorik = fordringIndberetHistorik;
    }

    public ServiceName getRequestType() {
        return requestType;
    }

    public void setRequestType(ServiceName requestType) {
        this.requestType = requestType;
    }

    public ModtagStatusOpdateringHistorik getModtagStatusOpdateringHistorik() {
        return modtagStatusOpdateringHistorik;
    }

    public void setModtagStatusOpdateringHistorik(ModtagStatusOpdateringHistorik modtagStatusOpdateringHistorik) {
        this.modtagStatusOpdateringHistorik = modtagStatusOpdateringHistorik;
    }

    public UnderretSamlingHentHistorik getUnderretSamlingHentHistorik() {
        return underretSamlingHentHistorik;
    }

    public void setUnderretSamlingHentHistorik(UnderretSamlingHentHistorik underretSamlingHentHistorik) {
        this.underretSamlingHentHistorik = underretSamlingHentHistorik;
    }

    public KvitteringHentHistorik getKvitteringHentHistorik() {
        return kvitteringHentHistorik;
    }

    public void setKvitteringHentHistorik(KvitteringHentHistorik kvitteringHentHistorik) {
        this.kvitteringHentHistorik = kvitteringHentHistorik;
    }

    public void addFordringIndberetHistorik(dk.rim.is.abt.nymf.entity.FordringIndberetHistorik fordringIndberetHistorik) {
        this.setFordringIndberetHistorik(fordringIndberetHistorik);
        //fordringIndberetHistorik.setTransaktionHistorik(this);
    }

    public void addModtagStatusOpdateringHistorik(ModtagStatusOpdateringHistorik modtagStatusOpdateringHistorik) {
        this.setModtagStatusOpdateringHistorik(modtagStatusOpdateringHistorik);
        modtagStatusOpdateringHistorik.setTransaktionHistorik(this);
    }

    public void addKvitteringHentHistorik(KvitteringHentHistorik kvitteringHentHistorik) {
        this.setKvitteringHentHistorik(kvitteringHentHistorik);
        kvitteringHentHistorik.setTransaktionHistorik(this);
    }

    public void addUnderretSamlingHentHistorik(UnderretSamlingHentHistorik underretSamlingHentHistorik) {
        this.setUnderretSamlingHentHistorik(underretSamlingHentHistorik);
        underretSamlingHentHistorik.setTransaktionHistorik(this);
    }

    public void addHistorik(IHistorikEntity entity) {
        if(entity instanceof UnderretSamlingHentHistorik){
            addUnderretSamlingHentHistorik((UnderretSamlingHentHistorik) entity);
        }
        else {
            addKvitteringHentHistorik((KvitteringHentHistorik) entity);
        }
    }

    public void addTransaktion(Transaktion transaktion) {
        this.setTransaktion(transaktion);
        transaktion.setTransaktionHistorik(this);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
