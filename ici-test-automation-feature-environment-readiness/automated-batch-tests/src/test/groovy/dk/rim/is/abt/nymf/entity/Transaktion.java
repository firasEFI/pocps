package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSAKTION",
        uniqueConstraints = {@UniqueConstraint(name="UK_TRANSAKTION_HIST_ID", columnNames = {"TRANSAKTION_HISTORIK_ID"})
        })
public class Transaktion implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSAKTION_GENERATOR")
    @SequenceGenerator(name = "TRANSAKTION_GENERATOR", sequenceName = "TRANSAKTION_SEQ", allocationSize=1)
    @Column
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="TRANSAKTION_HISTORIK_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_TRANSAKTION_HISTORIK"))
    private TransaktionHistorik transaktionHistorik;

    @Column
    private String transaktionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransaktionHistorik getTransaktionHistorik() {
        return transaktionHistorik;
    }

    public void setTransaktionHistorik(TransaktionHistorik transaktionHistorik) {
        this.transaktionHistorik = transaktionHistorik;
    }

    public String getTransaktionId() {
        return transaktionId;
    }

    public void setTransaktionId(String transaktionId) {
        this.transaktionId = transaktionId;
    }
}
