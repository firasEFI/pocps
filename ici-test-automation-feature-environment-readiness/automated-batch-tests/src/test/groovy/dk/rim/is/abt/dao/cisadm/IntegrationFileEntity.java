package dk.rim.is.abt.dao.cisadm;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by micw on 12-06-2017.
 */
@Entity
@Table(name = "INTEGRATION_FILE", schema = "CISADM", catalog = "")
public class IntegrationFileEntity {
    private long id;
    private String uuid;
    private long version;
    private long integrationFileTypeId;
    private byte[] rawFileContents;
    private String filename;
    private String step;
    private String status;
    private Timestamp statusTime;
    private String statusBy;
    private Timestamp fileGeneratedTime;
    private Timestamp createdTime;
    private String createdBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UUID", nullable = true, length = 255)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "VERSION", nullable = false, precision = 0)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "INTEGRATION_FILE_TYPE_ID", nullable = false, precision = 0)
    public long getIntegrationFileTypeId() {
        return integrationFileTypeId;
    }

    public void setIntegrationFileTypeId(long integrationFileTypeId) {
        this.integrationFileTypeId = integrationFileTypeId;
    }

    @Basic
    @Column(name = "RAW_FILE_CONTENTS", nullable = false)
    public byte[] getRawFileContents() {
        return rawFileContents;
    }

    public void setRawFileContents(byte[] rawFileContents) {
        this.rawFileContents = rawFileContents;
    }

    @Basic
    @Column(name = "FILENAME", nullable = false, length = 100)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "STEP", nullable = false, length = 50)
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
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
    @Column(name = "STATUS_TIME", nullable = false)
    public Timestamp getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Timestamp statusTime) {
        this.statusTime = statusTime;
    }

    @Basic
    @Column(name = "STATUS_BY", nullable = false, length = 50)
    public String getStatusBy() {
        return statusBy;
    }

    public void setStatusBy(String statusBy) {
        this.statusBy = statusBy;
    }

    @Basic
    @Column(name = "FILE_GENERATED_TIME", nullable = true)
    public Timestamp getFileGeneratedTime() {
        return fileGeneratedTime;
    }

    public void setFileGeneratedTime(Timestamp fileGeneratedTime) {
        this.fileGeneratedTime = fileGeneratedTime;
    }

    @Basic
    @Column(name = "CREATED_TIME", nullable = false)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "CREATED_BY", nullable = false, length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegrationFileEntity that = (IntegrationFileEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (integrationFileTypeId != that.integrationFileTypeId) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (!Arrays.equals(rawFileContents, that.rawFileContents)) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        if (step != null ? !step.equals(that.step) : that.step != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (statusTime != null ? !statusTime.equals(that.statusTime) : that.statusTime != null) return false;
        if (statusBy != null ? !statusBy.equals(that.statusBy) : that.statusBy != null) return false;
        if (fileGeneratedTime != null ? !fileGeneratedTime.equals(that.fileGeneratedTime) : that.fileGeneratedTime != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (int) (integrationFileTypeId ^ (integrationFileTypeId >>> 32));
        result = 31 * result + Arrays.hashCode(rawFileContents);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (statusTime != null ? statusTime.hashCode() : 0);
        result = 31 * result + (statusBy != null ? statusBy.hashCode() : 0);
        result = 31 * result + (fileGeneratedTime != null ? fileGeneratedTime.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }
}
