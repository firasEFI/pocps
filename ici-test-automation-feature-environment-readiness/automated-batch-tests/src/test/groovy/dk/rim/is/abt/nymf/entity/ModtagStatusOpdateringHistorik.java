package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MOD_STAT_OPD_HISTORIK",
        uniqueConstraints = {@UniqueConstraint(name="UK_TRANSAKTION_HISTORIK_ID", columnNames = {"TRANSAKTION_HISTORIK_ID"})
        })
public class ModtagStatusOpdateringHistorik implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOD_STAT_OPD_GENERATOR")
    @SequenceGenerator(name = "MOD_STAT_OPD_GENERATOR", sequenceName = "MOD_STAT_OPD_HIST_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String request;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String response;

    @OneToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name="TRANSAKTION_HISTORIK_ID", unique=true, nullable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_MOD_STAT_OPD_HISTORIK"))
    private TransaktionHistorik transaktionHistorik;

    @Column
    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
