package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.enums.XmlType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Leverance entity
 * <p>
 * Created by      skj
 * Creation date   11-11-2016
 */

@Entity
@Table(name = "LEVERANCE",
        uniqueConstraints = {@UniqueConstraint(name="UK_LEVERANCE_ID", columnNames = {"leveranceId"}),
                @UniqueConstraint(name="UK_STATE_ID", columnNames = {"STATE_ID"}),
                @UniqueConstraint(name="UK_FORDRING_INDBERET_HISTORIK", columnNames = {"FORDRING_INDBERET_HISTORIK_ID"})})
public class LeveranceEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEVERANCE_GENERATOR")
    @SequenceGenerator(name = "LEVERANCE_GENERATOR", sequenceName = "LEVERANCE_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Column
    private Long leveranceId;

    public LeveranceEntity(Long leveranceId) {
        this.leveranceId = leveranceId;
    }

    public LeveranceEntity(Long leveranceId, Long fordringshaverSystemId) {
        this.leveranceId = leveranceId;
        this.fordringshaverSystemId = fordringshaverSystemId;
    }

    public List<dk.rim.is.abt.nymf.entity.FordringsAktion> getFordringsAktioner() {
        return fordringsAktioner;
    }

    public void setFordringsAktioner(List<dk.rim.is.abt.nymf.entity.FordringsAktion> fordringsAktioner) {
        this.fordringsAktioner = fordringsAktioner;
    }

    public void addFordringsAktion(dk.rim.is.abt.nymf.entity.FordringsAktion fordringsAktion){
      //  fordringsAktion.setLeveranceEntity(this);
        this.fordringsAktioner.add(fordringsAktion);
    }

    @OneToMany(mappedBy = "leveranceEntity", cascade = CascadeType.ALL)
    private List<FordringsAktion> fordringsAktioner = new ArrayList<>();

    @Column
    private Long fordringshaverSystemId;

    @Column
    private LocalDateTime modtagelsesTidspunkt;

    @Column                     //  OIO- or SKAT-XML.
    @Enumerated(EnumType.STRING)
    private XmlType xmlType;

    @Column
    private int errorCode;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String exMfResponse;

    @OneToOne(fetch= FetchType.LAZY, optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name="FORDRING_INDBERET_HISTORIK_ID", unique=false, nullable=true, updatable=false, foreignKey = @ForeignKey(name = "FK_FORDRING_INDBERET_HISTORIK"))
    private dk.rim.is.abt.nymf.entity.FordringIndberetHistorik fordringIndberetHistorik;

    @OneToOne(fetch= FetchType.LAZY, optional=false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="STATE_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_STATE"))
    private LeveranceState leveranceState;

    @OneToOne(fetch= FetchType.LAZY, optional = false, mappedBy = "leveranceEntity", cascade = CascadeType.ALL)
    private SeenLeverance seenLeverance;

    public LeveranceEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeveranceId() {
        return leveranceId;
    }

    public void setLeveranceId(Long leveranceId) {
        this.leveranceId = leveranceId;
    }

    public Long getFordringshaverSystemId() {
        return fordringshaverSystemId;
    }

    public void setFordringshaverSystemId(Long fordringshaverSystemId) {
        this.fordringshaverSystemId = fordringshaverSystemId;
    }

    public LocalDateTime getModtagelsesTidspunkt() {
        return modtagelsesTidspunkt;
    }

    public void setModtagelsesTidspunkt(LocalDateTime modtagelsesTidspunkt) {
        this.modtagelsesTidspunkt = modtagelsesTidspunkt;
    }

    public XmlType getXmlType() {
        return xmlType;
    }

    public void setXmlType(XmlType xmlType) {
        this.xmlType = xmlType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int error) {
        this.errorCode = error;
    }


    public String getExMfResponse() {
        return exMfResponse;
    }

    public void setExMfResponse(String exMfResponse) {
        this.exMfResponse = exMfResponse;
    }

    public dk.rim.is.abt.nymf.entity.FordringIndberetHistorik getFordringIndberetHistorik() {
        return fordringIndberetHistorik;
    }

    public void setFordringIndberetHistorik(dk.rim.is.abt.nymf.entity.FordringIndberetHistorik fordringIndberetHistorik) {
        this.fordringIndberetHistorik = fordringIndberetHistorik;
    }

    public LeveranceState getLeveranceState() {
        return leveranceState;
    }

    public void setLeveranceState(LeveranceState leveranceState) {
        this.leveranceState = leveranceState;
    }

    public void setTransaktionId(String transaktionId) {
        this.getFordringIndberetHistorik().getTransaktionHistorik().getTransaktion().setTransaktionId(transaktionId);
    }
    public String getTransaktionId() {
        if(this.getFordringIndberetHistorik().getTransaktionHistorik().getTransaktion() != null){
            return this.getFordringIndberetHistorik().getTransaktionHistorik().getTransaktion().getTransaktionId();
        }
        return null;
    }

    @PrePersist
    void onPrePersist(){
        this.seenLeverance = new SeenLeverance(this);
    }
}