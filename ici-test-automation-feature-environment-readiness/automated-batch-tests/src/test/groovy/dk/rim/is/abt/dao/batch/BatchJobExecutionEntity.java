package dk.rim.is.abt.dao.batch;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BATCH_JOB_EXECUTION", schema = "BATCH", catalog = "")
public class BatchJobExecutionEntity {
    private long jobExecutionId;
    private Long version;
    private Timestamp createTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private String exitCode;
    private String exitMessage;
    private Timestamp lastUpdated;
    private String jobConfigurationLocation;

    @Id
    @Column(name = "JOB_EXECUTION_ID", nullable = false, precision = 0)
    public long getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, precision = 0)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "START_TIME", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "EXIT_CODE", nullable = true, length = 2500)
    public String getExitCode() {
        return exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    @Basic
    @Column(name = "EXIT_MESSAGE", nullable = true, length = 2500)
    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    @Basic
    @Column(name = "LAST_UPDATED", nullable = true)
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Basic
    @Column(name = "JOB_CONFIGURATION_LOCATION", nullable = true, length = 2500)
    public String getJobConfigurationLocation() {
        return jobConfigurationLocation;
    }

    public void setJobConfigurationLocation(String jobConfigurationLocation) {
        this.jobConfigurationLocation = jobConfigurationLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchJobExecutionEntity that = (BatchJobExecutionEntity) o;

        if (jobExecutionId != that.jobExecutionId) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (exitCode != null ? !exitCode.equals(that.exitCode) : that.exitCode != null) return false;
        if (exitMessage != null ? !exitMessage.equals(that.exitMessage) : that.exitMessage != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(that.lastUpdated) : that.lastUpdated != null) return false;
        if (jobConfigurationLocation != null ? !jobConfigurationLocation.equals(that.jobConfigurationLocation) : that.jobConfigurationLocation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (jobExecutionId ^ (jobExecutionId >>> 32));
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (exitCode != null ? exitCode.hashCode() : 0);
        result = 31 * result + (exitMessage != null ? exitMessage.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (jobConfigurationLocation != null ? jobConfigurationLocation.hashCode() : 0);
        return result;
    }
}
