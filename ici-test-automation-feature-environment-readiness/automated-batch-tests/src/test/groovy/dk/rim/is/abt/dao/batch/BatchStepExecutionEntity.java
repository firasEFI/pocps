package dk.rim.is.abt.dao.batch;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BATCH_STEP_EXECUTION", schema = "BATCH", catalog = "")
public class BatchStepExecutionEntity {
    private long stepExecutionId;
    private long version;
    private String stepName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private Long commitCount;
    private Long readCount;
    private Long filterCount;
    private Long writeCount;
    private Long readSkipCount;
    private Long writeSkipCount;
    private Long processSkipCount;
    private Long rollbackCount;
    private String exitCode;
    private String exitMessage;
    private Timestamp lastUpdated;

    @Id
    @Column(name = "STEP_EXECUTION_ID", nullable = false, precision = 0)
    public long getStepExecutionId() {
        return stepExecutionId;
    }

    public void setStepExecutionId(long stepExecutionId) {
        this.stepExecutionId = stepExecutionId;
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
    @Column(name = "STEP_NAME", nullable = false, length = 100)
    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Basic
    @Column(name = "START_TIME", nullable = false)
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
    @Column(name = "COMMIT_COUNT", nullable = true, precision = 0)
    public Long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(Long commitCount) {
        this.commitCount = commitCount;
    }

    @Basic
    @Column(name = "READ_COUNT", nullable = true, precision = 0)
    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    @Basic
    @Column(name = "FILTER_COUNT", nullable = true, precision = 0)
    public Long getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(Long filterCount) {
        this.filterCount = filterCount;
    }

    @Basic
    @Column(name = "WRITE_COUNT", nullable = true, precision = 0)
    public Long getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(Long writeCount) {
        this.writeCount = writeCount;
    }

    @Basic
    @Column(name = "READ_SKIP_COUNT", nullable = true, precision = 0)
    public Long getReadSkipCount() {
        return readSkipCount;
    }

    public void setReadSkipCount(Long readSkipCount) {
        this.readSkipCount = readSkipCount;
    }

    @Basic
    @Column(name = "WRITE_SKIP_COUNT", nullable = true, precision = 0)
    public Long getWriteSkipCount() {
        return writeSkipCount;
    }

    public void setWriteSkipCount(Long writeSkipCount) {
        this.writeSkipCount = writeSkipCount;
    }

    @Basic
    @Column(name = "PROCESS_SKIP_COUNT", nullable = true, precision = 0)
    public Long getProcessSkipCount() {
        return processSkipCount;
    }

    public void setProcessSkipCount(Long processSkipCount) {
        this.processSkipCount = processSkipCount;
    }

    @Basic
    @Column(name = "ROLLBACK_COUNT", nullable = true, precision = 0)
    public Long getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(Long rollbackCount) {
        this.rollbackCount = rollbackCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchStepExecutionEntity that = (BatchStepExecutionEntity) o;

        if (stepExecutionId != that.stepExecutionId) return false;
        if (version != that.version) return false;
        if (stepName != null ? !stepName.equals(that.stepName) : that.stepName != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (commitCount != null ? !commitCount.equals(that.commitCount) : that.commitCount != null) return false;
        if (readCount != null ? !readCount.equals(that.readCount) : that.readCount != null) return false;
        if (filterCount != null ? !filterCount.equals(that.filterCount) : that.filterCount != null) return false;
        if (writeCount != null ? !writeCount.equals(that.writeCount) : that.writeCount != null) return false;
        if (readSkipCount != null ? !readSkipCount.equals(that.readSkipCount) : that.readSkipCount != null)
            return false;
        if (writeSkipCount != null ? !writeSkipCount.equals(that.writeSkipCount) : that.writeSkipCount != null)
            return false;
        if (processSkipCount != null ? !processSkipCount.equals(that.processSkipCount) : that.processSkipCount != null)
            return false;
        if (rollbackCount != null ? !rollbackCount.equals(that.rollbackCount) : that.rollbackCount != null)
            return false;
        if (exitCode != null ? !exitCode.equals(that.exitCode) : that.exitCode != null) return false;
        if (exitMessage != null ? !exitMessage.equals(that.exitMessage) : that.exitMessage != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(that.lastUpdated) : that.lastUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (stepExecutionId ^ (stepExecutionId >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (stepName != null ? stepName.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (commitCount != null ? commitCount.hashCode() : 0);
        result = 31 * result + (readCount != null ? readCount.hashCode() : 0);
        result = 31 * result + (filterCount != null ? filterCount.hashCode() : 0);
        result = 31 * result + (writeCount != null ? writeCount.hashCode() : 0);
        result = 31 * result + (readSkipCount != null ? readSkipCount.hashCode() : 0);
        result = 31 * result + (writeSkipCount != null ? writeSkipCount.hashCode() : 0);
        result = 31 * result + (processSkipCount != null ? processSkipCount.hashCode() : 0);
        result = 31 * result + (rollbackCount != null ? rollbackCount.hashCode() : 0);
        result = 31 * result + (exitCode != null ? exitCode.hashCode() : 0);
        result = 31 * result + (exitMessage != null ? exitMessage.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
