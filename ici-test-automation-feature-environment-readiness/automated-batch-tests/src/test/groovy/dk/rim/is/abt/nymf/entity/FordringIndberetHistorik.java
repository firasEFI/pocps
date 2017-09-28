package dk.rim.is.abt.nymf.entity;


import dk.skat.begrebsmodel._2009._01._15.MFAktionStatusKodeType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FORDRING_INDBERET_HISTORIK",
        uniqueConstraints = {@UniqueConstraint(name="UK_TRANSAKTION_HISTORIK", columnNames = {"TRANSAKTOIN_HISTORIK_ID"})})
public class FordringIndberetHistorik implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORD_INDB_HIST_GENERATOR")
    @SequenceGenerator(name = "FORD_INDB_HIST_GENERATOR", sequenceName = "FORD_INDB_HIST_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String request;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String requestHeader;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String response;

    @OneToOne(fetch = FetchType.LAZY, optional=true, mappedBy="fordringIndberetHistorik")
    private LeveranceEntity leveracEntity;

    @OneToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name="TRANSAKTOIN_HISTORIK_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_FORDRING_INDB_HIST"))
    private TransaktionHistorik transaktionHistorik;

    @Column
    private int errorCode;

    @Column
    @Enumerated(EnumType.STRING)
    private MFAktionStatusKodeType status;

    @Column
    private LocalDateTime statusAendretDato;

    @Column
    private Long leveranceId;

    @Column
    private LocalDateTime timestamp;


    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeveranceEntity getLeveracEntity() {
        return leveracEntity;
    }

    public void setLeveracEntity(LeveranceEntity leveracEntity) {
        this.leveracEntity = leveracEntity;
    }

    public TransaktionHistorik getTransaktionHistorik() {
        return transaktionHistorik;
    }

    public void setTransaktionHistorik(TransaktionHistorik transaktionHistorik) {
        this.transaktionHistorik = transaktionHistorik;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLeveranceId() {
        return leveranceId;
    }

    public void setLeveranceId(Long leveranceId) {
        this.leveranceId = leveranceId;
    }

    public String getResponse() {
        return response;
    }

    public void addLeverance(LeveranceEntity leverance) {
        this.setLeveracEntity(leverance);
        leverance.setFordringIndberetHistorik(this);
    }

    public void remove(LeveranceEntity leverance) {
        this.setLeveracEntity(null);
        leverance.setFordringIndberetHistorik(null);
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public MFAktionStatusKodeType getStatus() {
        return status;
    }

    public void setStatus(MFAktionStatusKodeType status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getStatusAendretDato() {
        return statusAendretDato;
    }

    public void setStatusAendretDato(LocalDateTime statusAendretDato) {
        this.statusAendretDato = statusAendretDato;
    }
}
