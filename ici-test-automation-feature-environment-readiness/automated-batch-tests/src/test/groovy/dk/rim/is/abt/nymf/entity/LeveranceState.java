package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;

@Entity
@Table(name = "LEVERANCE_STATE")
public class LeveranceState implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEVERANCE_STAT_GENERATOR")
    @SequenceGenerator(name = "LEVERANCE_STAT_GENERATOR", sequenceName = "LEVERANCE_STAT_SEQ", allocationSize=1)
    @Column
    private Long id;


    @Column
    @Enumerated(EnumType.STRING)
    private dk.rim.is.api.enums.LeveranceStatus exMfLeveranceStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private dk.rim.is.api.enums.LeveranceStatus psrmLeveranceStatus;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public dk.rim.is.api.enums.LeveranceStatus getPsrmLeveranceStatus() {
        return psrmLeveranceStatus;
    }

    public void setPsrmLeveranceStatus(dk.rim.is.api.enums.LeveranceStatus psrmLeveranceStatus) {
        this.psrmLeveranceStatus = psrmLeveranceStatus;
    }

    public dk.rim.is.api.enums.LeveranceStatus getExMfLeveranceStatus() {
        return exMfLeveranceStatus;
    }

    public void setExMfLeveranceStatus(dk.rim.is.api.enums.LeveranceStatus exMfLeveranceStatus) {
        this.exMfLeveranceStatus = exMfLeveranceStatus;
    }
}
