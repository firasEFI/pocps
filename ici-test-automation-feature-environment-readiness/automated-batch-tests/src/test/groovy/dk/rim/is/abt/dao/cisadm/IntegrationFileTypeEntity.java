package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by micw on 12-06-2017.
 */
@Entity
@Table(name = "INTEGRATION_FILE_TYPE", schema = "CISADM", catalog = "")
public class IntegrationFileTypeEntity {
    private long id;
    private long version;
    private String externalSystemName;
    private String fileTypeName;
    private String sourceSystemName;
    private String destinationSystemName;
    private String fileFormat;
    private String direction;

    @Id
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

    @Basic
    @Column(name = "EXTERNAL_SYSTEM_NAME", nullable = false, length = 50)
    public String getExternalSystemName() {
        return externalSystemName;
    }

    public void setExternalSystemName(String externalSystemName) {
        this.externalSystemName = externalSystemName;
    }

    @Basic
    @Column(name = "FILE_TYPE_NAME", nullable = false, length = 50)
    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    @Basic
    @Column(name = "SOURCE_SYSTEM_NAME", nullable = false, length = 50)
    public String getSourceSystemName() {
        return sourceSystemName;
    }

    public void setSourceSystemName(String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }

    @Basic
    @Column(name = "DESTINATION_SYSTEM_NAME", nullable = false, length = 50)
    public String getDestinationSystemName() {
        return destinationSystemName;
    }

    public void setDestinationSystemName(String destinationSystemName) {
        this.destinationSystemName = destinationSystemName;
    }

    @Basic
    @Column(name = "FILE_FORMAT", nullable = false, length = 50)
    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Basic
    @Column(name = "DIRECTION", nullable = false, length = 50)
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegrationFileTypeEntity that = (IntegrationFileTypeEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (externalSystemName != null ? !externalSystemName.equals(that.externalSystemName) : that.externalSystemName != null)
            return false;
        if (fileTypeName != null ? !fileTypeName.equals(that.fileTypeName) : that.fileTypeName != null) return false;
        if (sourceSystemName != null ? !sourceSystemName.equals(that.sourceSystemName) : that.sourceSystemName != null)
            return false;
        if (destinationSystemName != null ? !destinationSystemName.equals(that.destinationSystemName) : that.destinationSystemName != null)
            return false;
        if (fileFormat != null ? !fileFormat.equals(that.fileFormat) : that.fileFormat != null) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (externalSystemName != null ? externalSystemName.hashCode() : 0);
        result = 31 * result + (fileTypeName != null ? fileTypeName.hashCode() : 0);
        result = 31 * result + (sourceSystemName != null ? sourceSystemName.hashCode() : 0);
        result = 31 * result + (destinationSystemName != null ? destinationSystemName.hashCode() : 0);
        result = 31 * result + (fileFormat != null ? fileFormat.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
