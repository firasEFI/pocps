package dk.rim.is.abt.dao.batch;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CORRECTION_ORDER", schema = "BATCH", catalog = "")
public class CorrectionOrderEntity {
    private long id;
    private long version;
    private Timestamp correctionsDate;
    private String orderIdentifier;
    private String status;
    private long counter;
    private long priority;
    private Long integrationFileId;

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VERSION", nullable = false, precision = 0)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Basic()
    @Column(name = "CORRECTIONS_DATE", nullable = true)
    public Timestamp getCorrectionsDate() {
        return correctionsDate;
    }

    public void setCorrectionsDate(Timestamp correctionsDate) {
        this.correctionsDate = correctionsDate;
    }

    @Basic()
    @Column(name = "ORDER_IDENTIFIER", nullable = true, length = 255)
    public String getOrderIdentifier() {
        return orderIdentifier;
    }

    public void setOrderIdentifier(String orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "COUNTER", nullable = false, precision = 0)
    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = false, precision = 0)
    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorrectionOrderEntity that = (CorrectionOrderEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (counter != that.counter) return false;
        if (priority != that.priority) return false;
        if (correctionsDate != null ? !correctionsDate.equals(that.correctionsDate) : that.correctionsDate != null)
            return false;
        if (orderIdentifier != null ? !orderIdentifier.equals(that.orderIdentifier) : that.orderIdentifier != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (correctionsDate != null ? correctionsDate.hashCode() : 0);
        result = 31 * result + (orderIdentifier != null ? orderIdentifier.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (counter ^ (counter >>> 32));
        result = 31 * result + (int) (priority ^ (priority >>> 32));
        return result;
    }

    @Basic
    @Column(name = "INTEGRATION_FILE_ID", nullable = true, precision = 0)
    public Long getIntegrationFileId() {
        return integrationFileId;
    }

    public void setIntegrationFileId(Long integrationFileId) {
        this.integrationFileId = integrationFileId;
    }
}
