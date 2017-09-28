package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA. Please write your own text here.
 * User: brj@netcompany.com
 * Date: 2017-03-23
 */

@Entity
@Table(name = "UNDERRET_SAMLING_HENT_HISTORIK",
        uniqueConstraints = {@UniqueConstraint(name="UK_USH_TRANSAKTION_HISTORIK_ID", columnNames = {"TRANSAKTION_HISTORIK_ID"})
        })
public class UnderretSamlingHentHistorik implements IHistorikEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOD_STAT_OPD_GENERATOR")
    @SequenceGenerator(name = "MOD_STAT_OPD_GENERATOR", sequenceName = "MOD_STAT_OPD_HIST_SEQ", allocationSize=1)
    @Column
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name="TRANSAKTION_HISTORIK_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "UNDERRET_SAMLING_HENT_HISTORIK"))
    private TransaktionHistorik transaktionHistorik;

    @Column
    private Long systemIndberetterId;

    @Column
    String result;

    @Column
    private Integer antalUnderretninger;

    @Column
    private LocalDateTime timestamp;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public TransaktionHistorik getTransaktionHistorik() {
        return transaktionHistorik;
    }

    @Override
    public void setTransaktionHistorik(TransaktionHistorik transaktionHistorik) {
        this.transaktionHistorik = transaktionHistorik;
    }

    @Override
    public Long getSystemIndberetterId() {
        return systemIndberetterId;
    }

    @Override
    public void setSystemIndberetterId(Long systemIndberetterId) {
        this.systemIndberetterId = systemIndberetterId;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }

    public Integer getAntalUnderretninger() {
        return antalUnderretninger;
    }

    public void setAntalUnderretninger(Integer antalUnderretninger) {
        this.antalUnderretninger = antalUnderretninger;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
