package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;

@Entity
@Table(name = "SEEN_LEVERANCE", uniqueConstraints={
        @UniqueConstraint(name="UK_LEVERANCE_ID_II", columnNames={"LEVERANCE_ID"}),
        @UniqueConstraint(name="UK_LEVERANCE_SYS_ID", columnNames={"LEVERANCE_ID", "FORDINGSHAVER_SYS_ID"})
})
public class SeenLeverance implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEEN_LEVERANCE_GENERATOR")
    @SequenceGenerator(name = "SEEN_LEVERANCE_GENERATOR", sequenceName = "SEEN_LEVERANCE_SEQ", allocationSize=1)
    @Column
    private Long id;

    @Column(name = "LEVERANCE_ID")
    private Long leveranceId;

    @Column(name = "FORDINGSHAVER_SYS_ID")
    private Long fordringshaverSystemId;

    @OneToOne(fetch= FetchType.LAZY, optional = true)
    @JoinColumn(name="FK_LEVERANCE_ID", updatable=false, foreignKey = @ForeignKey(name = "FK_LEVERANCE_ID_II"))
    private dk.rim.is.abt.nymf.entity.LeveranceEntity leveranceEntity;

    public SeenLeverance() {}

    public SeenLeverance(dk.rim.is.abt.nymf.entity.LeveranceEntity leveranceEntity) {
        this.leveranceId = leveranceEntity.getLeveranceId();
        this.fordringshaverSystemId = leveranceEntity.getFordringshaverSystemId();
        this.leveranceEntity = leveranceEntity;
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

    public dk.rim.is.abt.nymf.entity.LeveranceEntity getLeveranceEntity() {
        return leveranceEntity;
    }

    public void setLeveranceEntity(LeveranceEntity leveranceEntity) {
        this.leveranceEntity = leveranceEntity;
    }
}