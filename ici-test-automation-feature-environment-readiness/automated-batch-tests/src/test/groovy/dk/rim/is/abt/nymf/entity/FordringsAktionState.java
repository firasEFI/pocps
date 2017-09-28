package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.enums.BatchError;
import dk.rim.is.api.enums.BatchStatus;
import dk.rim.is.api.enums.BatchTargetSystem;
import dk.skat.begrebsmodel._2009._01._15.MFAktionStatusKodeType;
import ws.binding.psrm.MFHoeringStatusKodeType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FORDRINGS_AKTION_STATE")
public class FordringsAktionState implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AKTION_STAT_GENERATOR")
    @SequenceGenerator(name = "AKTION_STAT_GENERATOR", sequenceName = "AKTION_STAT_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private MFAktionStatusKodeType status;

    @Column
    private boolean hoeringFlag;

    @Column
    private int errorCode;

    @Column
    private LocalDateTime statusAendretDato;

    @Column
    @Enumerated(EnumType.STRING)
    private BatchStatus batchStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private BatchTargetSystem batchTargetSystem;

    @Column
    @Enumerated(EnumType.STRING)
    private BatchError batchError;

    @Column
    @Enumerated(EnumType.STRING)
    private MFHoeringStatusKodeType hoeringStatus;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private String filterResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MFAktionStatusKodeType getStatus() {
        return status;
    }

    public void setStatus(MFAktionStatusKodeType status) {
        this.status = status;
    }

    public LocalDateTime getStatusAendretDato() {
        return statusAendretDato;
    }

    public void setStatusAendretDato(LocalDateTime statusAendretDato) {
        this.statusAendretDato = statusAendretDato;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public BatchStatus getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(BatchStatus batchStatus) {
        this.batchStatus = batchStatus;
    }

    public BatchError getBatchError() {
        return batchError;
    }

    public void setBatchError(BatchError batchError) {
        this.batchError = batchError;
    }

    public BatchTargetSystem getBatchTargetSystem() {
        return batchTargetSystem;
    }

    public void setBatchTargetSystem(BatchTargetSystem batchTargetSystem) {
        this.batchTargetSystem = batchTargetSystem;
    }

    public boolean getHoeringFlag() {
        return hoeringFlag;
    }

    public void setHoeringFlag(boolean hoeringFlag) {
        this.hoeringFlag = hoeringFlag;
    }

    public void setHoeringStatus(MFHoeringStatusKodeType hoeringStatus) {
        this.hoeringStatus = hoeringStatus;
    }

    public MFHoeringStatusKodeType getHoeringStatus() {
        return hoeringStatus;
    }

    public String getFilterResult() {
        return filterResult;
    }

    public void setFilterResult(String filterResult) {  this.filterResult = filterResult; }

    @Override
    public String toString() {
        return "FordringsAktionState{" + "id=" + id + ", status=" + status + ", hoeringFlag=" + hoeringFlag + ", errorCode=" + errorCode + ", statusAendretDato=" + statusAendretDato + ", batchStatus=" + batchStatus + ", batchTargetSystem=" + batchTargetSystem + ", batchError=" + batchError + '}';
    }
}
