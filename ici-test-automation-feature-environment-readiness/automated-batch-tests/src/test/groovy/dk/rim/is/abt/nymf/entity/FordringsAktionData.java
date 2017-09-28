package dk.rim.is.abt.nymf.entity;

import javax.persistence.*;

/**
 * Created by xtda on 08-05-2017.
 */
@Entity
@Table(name = "FORDRINGS_AKTION_DATA")
public class FordringsAktionData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AKTION_DATA_GENERATOR")
    @SequenceGenerator(name = "AKTION_DATA_GENERATOR", sequenceName = "AKTION_DATA_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "clob")
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FordringsAktionData{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
