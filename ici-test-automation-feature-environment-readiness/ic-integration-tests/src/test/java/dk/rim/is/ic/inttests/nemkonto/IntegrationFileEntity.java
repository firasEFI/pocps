package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus;
import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStep;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INTEGRATION_FILE", schema = "CISADM")
public class IntegrationFileEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @Column(name = "UUID")
    private String uuid;

    @Lob
    @Column(name = "RAW_FILE_CONTENTS", nullable = false)
    private byte[] rawFileContents;

    @Column(name = "FILENAME", nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(name = "STEP", nullable = false)
    private IntegrationFileStep step;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private IntegrationFileStatus status;

    @Column(name = "STATUS_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusTime;

    @Column(name = "STATUS_BY", nullable = false)
    private String statusBy;

    @Column(name = "FILE_GENERATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileGeneratedTime;

    @Column(name = "CREATED_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTEGRATION_FILE_TYPE_ID", nullable = false)
    private IntegrationFileTypeEntity integrationFileType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public IntegrationFileTypeEntity getIntegrationFileType() {
        return integrationFileType;
    }

    public void setIntegrationFileType(IntegrationFileTypeEntity integrationFileType) {
        this.integrationFileType = integrationFileType;
    }

    public byte[] getRawFileContents() {
        return rawFileContents;
    }

    public void setRawFileContents(byte[] rawFileContents) {
        this.rawFileContents = rawFileContents;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public IntegrationFileStep getStep() {
        return step;
    }

    public void setStep(IntegrationFileStep step) {
        this.step = step;
    }

    public IntegrationFileStatus getStatus() {
        return status;
    }

    public void setStatus(IntegrationFileStatus status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public String getStatusBy() {
        return statusBy;
    }

    public void setStatusBy(String statusBy) {
        this.statusBy = statusBy;
    }

    public Date getFileGeneratedTime() {
        return fileGeneratedTime;
    }

    public void setFileGeneratedTime(Date fileGeneratedTime) {
        this.fileGeneratedTime = fileGeneratedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("version", version)
                .append("uuid", uuid)
                .append("fileName", fileName)
                .append("step", step)
                .append("status", status)
                .append("statusTime", statusTime)
                .append("statusBy", statusBy)
                .append("fileGeneratedTime", fileGeneratedTime)
                .append("createdTime", createdTime)
                .append("createdBy", createdBy)
                .toString();
    }
}
