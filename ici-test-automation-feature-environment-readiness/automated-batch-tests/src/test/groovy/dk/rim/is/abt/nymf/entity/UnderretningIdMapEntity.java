package dk.rim.is.abt.nymf.entity;

import dk.rim.is.api.enums.BatchTargetSystem;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Underretning entity
 * <p>
 * Created by      skj
 * Creation date   11-11-2016
 */

@Entity
@Table(name = "UNDERRETNING_ID_MAP",
        uniqueConstraints={
            @UniqueConstraint(name="UK_UNDERRETNING_ID", columnNames = {"PSRMUNDERRETNINGID","EXMFUNDERRETNINGID","SYSTEMINDBERETTERID"}),
            @UniqueConstraint(name="NYMFUNDERRETNINGID",columnNames = {"NYMFUNDERRETNINGID"})
        })
public class UnderretningIdMapEntity implements IEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNDERRETNING_GENERATOR")
    @SequenceGenerator(name = "UNDERRETNING_GENERATOR", sequenceName = "UNDERRETNING_SEQ", allocationSize=1)
    @Column
    private Long Id;

    public UnderretningIdMapEntity() {
    }

    @Column(columnDefinition = "Numeric(24,0)")
    private BigInteger nymfUnderretningId;

    @Column
    private Long exmfUnderretningId;

    @Column
    private Long psrmUnderretningId;

    @Column
    @Enumerated(EnumType.STRING)
    private BatchTargetSystem system;

    @Column
    private Long fordringhaverId;

    @Column
    private Long systemIndberetterId;

    @Column
    private LocalDateTime mfUnderretningDatoTid;

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
    }

    @Column
    private LocalDateTime underretningRequestTime;

    public BigInteger getNymfUnderretningId() {
        return nymfUnderretningId;
    }

    public void setNymfUnderretningId(BigInteger nymfUnderretningId) {
        this.nymfUnderretningId = nymfUnderretningId;
    }

    public Long getExmfUnderretningId() {
        return exmfUnderretningId;
    }

    public void setExmfUnderretningId(Long exmfUnderretningId) {
        this.exmfUnderretningId = exmfUnderretningId;
    }

    public Long getPsrmUnderretningId() {
        return psrmUnderretningId;
    }

    public void setPsrmUnderretningId(Long psrmUnderretningId) {
        this.psrmUnderretningId = psrmUnderretningId;
    }

    public BatchTargetSystem getSystem() {
        return system;
    }

    public void setSystem(BatchTargetSystem system) {
        this.system = system;
    }

    public Long getFordringhaverId() {
        return fordringhaverId;
    }

    public void setFordringhaverId(Long fordringhaverId) {
        this.fordringhaverId = fordringhaverId;
    }

    public Long getSystemIndberetterId() {
        return systemIndberetterId;
    }

    public void setSystemIndberetterId(Long systemIndberetterId) {
        this.systemIndberetterId = systemIndberetterId;
    }

    public LocalDateTime getMfUnderretningDatoTid() {
        return mfUnderretningDatoTid;
    }

    public void setMfUnderretningDatoTid(LocalDateTime mfUnderretningDatoTid) {
        this.mfUnderretningDatoTid = mfUnderretningDatoTid;
    }

    public LocalDateTime getUnderretningRequestTime() {
        return underretningRequestTime;
    }

    public void setUnderretningRequestTime(LocalDateTime underretningRequestTime) {
        this.underretningRequestTime = underretningRequestTime;
    }

    @Override
    public String toString() {
        return "UnderretningIdMapEntity{" + "Id=" + Id + ", nymfUnderretningId=" + nymfUnderretningId + ", exmfUnderretningId=" + exmfUnderretningId + ", psrmUnderretningId=" + psrmUnderretningId + ", system=" + system + ", fordringhaverId=" + fordringhaverId + ", systemIndberetterId=" + systemIndberetterId + ", mfUnderretningDatoTid=" + mfUnderretningDatoTid + '}';
    }
}