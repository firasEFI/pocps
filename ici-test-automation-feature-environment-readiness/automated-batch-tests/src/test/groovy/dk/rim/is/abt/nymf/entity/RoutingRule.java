package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.enums.BatchTargetSystem;
import dk.rim.is.api.enums.FordringsRelation;
import dk.rim.is.api.enums.KundeType;
import dk.skat.begrebsmodel._2009._01._15.DMIFordringArtType;
import dk.skat.begrebsmodel._2009._01._15.MFAktionKodeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * (Beskrivelse af klasse)
 * <p>
 * Created by      frbm
 * Creation date   26-01-2017
 */
@Entity
public class RoutingRule implements IEntity {

    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private MFAktionKodeType aktionKode;

    @Enumerated(EnumType.STRING)
    private FordringsRelation fordringsRelation;

    @Enumerated(EnumType.STRING)
    private DMIFordringArtType artType;

    @Enumerated(EnumType.STRING)
    private KundeType kundeType;

    private boolean oneHaefter;

    @Enumerated(EnumType.STRING)
    private BatchTargetSystem batchTargetSystem;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public MFAktionKodeType getAktionKode() {
        return aktionKode;
    }

    public void setAktionKode(MFAktionKodeType aktionKode) {
        this.aktionKode = aktionKode;
    }

    public FordringsRelation getFordringsRelation() {
        return fordringsRelation;
    }

    public void setFordringsRelation(FordringsRelation hfOrUf) {
        this.fordringsRelation = hfOrUf;
    }

    public DMIFordringArtType getArtType() {
        return artType;
    }

    public void setArtType(DMIFordringArtType artType) {
        this.artType = artType;
    }

    public KundeType getKundeType() {
        return kundeType;
    }

    public void setKundeType(KundeType kundeType) {
        this.kundeType = kundeType;
    }

    public boolean isOneHaefter() {
        return oneHaefter;
    }

    public void setOneHaefter(boolean oneHaefter) {
        this.oneHaefter = oneHaefter;
    }

    public BatchTargetSystem getBatchTargetSystem() {
        return batchTargetSystem;
    }

    public void setBatchTargetSystem(BatchTargetSystem targetSystem) {
        this.batchTargetSystem = targetSystem;
    }
}
