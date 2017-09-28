package dk.rim.is.abt.dao.batch;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entity class for mapping properties held in BATCH_PROPERTY table.
 */
@Entity
@Table(name = "DATE_STORE", schema = "BATCH")
public class DateStoreEntity {
    private Long id;
    private String name;
    private Date storedDate;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STORED_DATE")
    public Date getStoredDate() {
        return storedDate;
    }

    public void setStoredDate(Date storedDate) {
        this.storedDate = storedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("storedDate", storedDate)
                .toString();
    }
}


