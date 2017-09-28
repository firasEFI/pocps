package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;


/**
 * Created by NFA on 02-01-2017.
 * En simpel klasse der beskriver mapning mellem Ny MF og ExMF aktions Id'er.
 */
@Entity
@Table(name = "AKTIONIDMAP")
public class AktionIdMapEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AKTIONIDMAP_GENERATOR")
    @SequenceGenerator(name = "AKTIONIDMAP_GENERATOR", sequenceName = "AKTIONIDMAP_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Column
    private Long nyMfAktionId;

    @Column
    private Long exMfAktionId;

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getNyMfAktionId() {
        return nyMfAktionId;
    }

    public void setNyMfAktionId(Long nyMfAktionId) {
        this.nyMfAktionId = nyMfAktionId;
    }

    public Long getExMfAktionId() {
        return exMfAktionId;
    }

    public void setExMfAktionId(Long exMfAktionId) {
        this.exMfAktionId = exMfAktionId;
    }
}
