package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Table(name = "INTEGRATION_FILE_TYPE", schema = "CISADM")
public class IntegrationFileTypeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @Column(name = "EXTERNAL_SYSTEM_NAME", nullable = false)
    private String externalSystemName;

    @Column(name = "FILE_TYPE_NAME", nullable = false)
    @Enumerated(EnumType.STRING)
    private IntegrationFileTypeName fileTypeName;

    @Column(name = "SOURCE_SYSTEM_NAME", nullable = false)
    private String sourceSystemName;

    @Column(name = "DESTINATION_SYSTEM_NAME", nullable = false)
    private String destinationSystemName;

    @Column(name = "FILE_FORMAT", nullable = false)
    private String fileFormat;

    @Column(name = "DIRECTION", nullable = false)
    private String direction;

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

    public String getExternalSystemName() {
        return externalSystemName;
    }

    public void setExternalSystemName(String externalSystemName) {
        this.externalSystemName = externalSystemName;
    }

    public IntegrationFileTypeName getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(IntegrationFileTypeName fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getSourceSystemName() {
        return sourceSystemName;
    }

    public void setSourceSystemName(String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }

    public String getDestinationSystemName() {
        return destinationSystemName;
    }

    public void setDestinationSystemName(String destinationSystemName) {
        this.destinationSystemName = destinationSystemName;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("version", version)
                .append("externalSystemName", externalSystemName)
                .append("fileTypeName", fileTypeName)
                .append("sourceSystemName", sourceSystemName)
                .append("destinationSystemName", destinationSystemName)
                .append("fileFormat", fileFormat)
                .append("direction", direction)
                .toString();
    }
}
