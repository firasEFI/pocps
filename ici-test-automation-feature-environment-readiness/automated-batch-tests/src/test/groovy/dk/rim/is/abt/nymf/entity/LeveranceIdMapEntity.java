package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;


/**
 * Created by NFA on 02-01-2017.
 * En simpel klasse der beskriver mapning mellem Ny MF og ExMF aktions Id'er.
 */
@Entity
@Table(name = "LEVERANCEIDMAP")
public abstract class LeveranceIdMapEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEVERANCEIDMAP_GENERATOR")
    @SequenceGenerator(name = "LEVERANCEIDMAP_GENERATOR", sequenceName = "LEVERANCEIDMAP_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Column
    private Long nyMfLeveranceId;

    @Column
    private Long oprindeligtLeveranceId;

    @Override
    public Long getId() { return id; }

    public Long getNyMFLeveranceId() {
        return nyMfLeveranceId;
    }

    public void setNyMFLeveranceId(Long nyMfLeveranceId) {
        this.nyMfLeveranceId = nyMfLeveranceId;
    }

    public Long getOprindeligtLeveranceId() {
        return oprindeligtLeveranceId;
    }

    public void setOprindeligtLeveranceId(Long oprindeligtLeveranceId) {
        this.oprindeligtLeveranceId = oprindeligtLeveranceId;
    }
}
