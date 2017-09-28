package dk.rim.is.abt.nymf.entity;


import dk.rim.is.api.enums.KundeType;

import javax.persistence.*;

@Entity
@Table(name = "SKYLDNER")
public class Skyldner implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKYLDNER_GENERATOR")
    @SequenceGenerator(name = "SKYLDNER_GENERATOR", sequenceName = "SKYLDNER_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column(length = 50)
    private String skyldnerId;

    @Column
    @Enumerated(EnumType.STRING)
    private KundeType skyldnerType;

    @Column(name="FORDRINGS_AKTION_ID")
    private Long fordringsAktionId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSkyldnerId() {
        return skyldnerId;
    }

    public void setSkyldnerId(String skyldnerId) {
        this.skyldnerId = skyldnerId;
    }

    public KundeType getSkyldnerType() {
        return skyldnerType;
    }

    public void setSkyldnerType(KundeType skyldnerType) {
        this.skyldnerType = skyldnerType;
    }

}
