package dk.rim.is.abt.dao.batch;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * Genereret brev PDF entity.
 */
@Entity
@Table(name = "GENERERET_BREV_PDF", schema = "BATCH")
public class GenereretBrevPdfEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private long version;

    @Column(name = "FORMATERET_MEDDELELSE_ID")
    private Long formateretMeddelelseId;

    @Lob
    @Column(name = "BREV_PDF")
    private byte[] brevPdf;

    @Column(name = "STATUS")
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENERERET_BREV_ID")
    private GenereretBrevEntity genereretBrev;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormateretMeddelelseId() {
        return formateretMeddelelseId;
    }

    public void setFormateretMeddelelseId(Long formateretMeddelelseId) {
        this.formateretMeddelelseId = formateretMeddelelseId;
    }

    public byte[] getBrevPdf() {
        return brevPdf;
    }

    public void setBrevPdf(byte[] brevPdf) {
        this.brevPdf = brevPdf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GenereretBrevEntity getGenereretBrev() {
        return genereretBrev;
    }

    public void setGenereretBrev(GenereretBrevEntity genereretBrev) {
        this.genereretBrev = genereretBrev;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("version", version)
                .append("formateretMeddelelseId", formateretMeddelelseId)
                .append("status", status)
                .toString();
    }
}
