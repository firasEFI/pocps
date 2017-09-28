package dk.rim.is.abt.dao.batch;

import dk.rim.is.common.entity.brev.CaptiaStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Genereret brev entity.
 */
@Entity
@Table(name = "GENERERET_BREV", schema = "BATCH", catalog = "")
public class GenereretBrevEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private long version;

    @Column(name = "FORMATERET_MEDDELELSE_ID")
    private Long formateretMeddelelseId;

    @Column(name = "STATUS_FORSENDELSE")
    private String statusForsendelse;

    @Column(name = "DATO_AFSENDELSE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoAfsendelse;

    @Column(name = "FORSENDELSE_FEJL_KODE")
    private String forsendelseFejlKode;

    @Lob
    @Column(name = "FORSENDELSE_FEJL_TEKST")
    private String forsendelseFejlTekst;

    @Column(name = "STATUS_CAPTIA")
    private String statusCaptia;

    @Column(name = "CAPTIA_DOKUMENT_NUMMER")
    private Long captiaDokumentNummer;

    @Column(name = "CAPTIA_AKT_NUMMER", precision = 22)
    private BigDecimal captiaAktNummer;

    @Column(name = "DATO_CAPTIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoCaptia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BREV_ID")
    private BrevEntity brev;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "genereretBrev", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private GenereretBrevPdfEntity genereretBrevPdf;

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

    public String getStatusForsendelse() {
        return statusForsendelse;
    }

    public void setStatusForsendelse(String statusForsendelse) {
        this.statusForsendelse = statusForsendelse;
    }

    public String getForsendelseFejlKode() {
        return forsendelseFejlKode;
    }

    public void setForsendelseFejlKode(String forsendelseFejlKode) {
        this.forsendelseFejlKode = forsendelseFejlKode;
    }

    public String getForsendelseFejlTekst() {
        return forsendelseFejlTekst;
    }

    public void setForsendelseFejlTekst(String forsendelseFejlTekst) {
        this.forsendelseFejlTekst = forsendelseFejlTekst;
    }

    public String getStatusCaptia() {
        return statusCaptia;
    }

    public void setStatusCaptia(CaptiaStatus statusCaptia) {
        this.statusCaptia = statusCaptia.getStatus();
    }

    public Long getCaptiaDokumentNummer() {
        return captiaDokumentNummer;
    }

    public void setCaptiaDokumentNummer(Long captiaDokumentNummer) {
        this.captiaDokumentNummer = captiaDokumentNummer;
    }

    public BigDecimal getCaptiaAktNummer() {
        return captiaAktNummer;
    }

    public void setCaptiaAktNummer(BigDecimal captiaAktNummer) {
        this.captiaAktNummer = captiaAktNummer;
    }

    public Date getDatoCaptia() {
        return datoCaptia;
    }

    public void setDatoCaptia(Date datoCaptia) {
        this.datoCaptia = datoCaptia;
    }

    public BrevEntity getBrev() {
        return brev;
    }

    public void setBrev(BrevEntity brev) {
        this.brev = brev;
    }

    public GenereretBrevPdfEntity getGenereretBrevPdf() {
        return genereretBrevPdf;
    }

    public void setGenereretBrevPdf(GenereretBrevPdfEntity genereretBrevPdf) {
        this.genereretBrevPdf = genereretBrevPdf;
    }

    public Date getDatoAfsendelse() {
        return datoAfsendelse;
    }

    public void setDatoAfsendelse(Date datoAfsendelse) {
        this.datoAfsendelse = datoAfsendelse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("version", version)
                .append("formateretMeddelelseId", formateretMeddelelseId)
                .append("statusForsendelse", statusForsendelse)
                .append("datoAfsendelse", datoAfsendelse)
                .append("forsendelseFejlKode", forsendelseFejlKode)
                .append("forsendelseFejlTekst", forsendelseFejlTekst)
                .append("statusCaptia", statusCaptia)
                .append("captiaDokumentNummer", captiaDokumentNummer)
                .append("captiaAktNummer", captiaAktNummer)
                .append("datoCaptia", datoCaptia)
                .toString();
    }
}
