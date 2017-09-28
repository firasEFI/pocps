package dk.rim.is.abt.dao.batch;

import dk.rim.is.clock.TimeTravelDateProvider;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Brev entity.
 */
@Entity
@Table(name = "BREV", schema = "BATCH")
public class BrevEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PK_SEQ")
    @SequenceGenerator(name = "PK_SEQ", sequenceName = "PK_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private long version;

    @Column(name = "BATCH_ID")
    private Long batchId;

    @Column(name = "BREV_ID")
    private String brevId;

    @Column(name = "DATO_OPRETTELSE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoOprettelse;

    @Column(name = "DATO_AOGD_STATUS_KALD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoAogDStatusKald;

    @Column(name = "DATO_OPDATERET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datoOpdateret;

    @Lob
    @Column(name = "XML")
    private String xml;

    @Column(name = "STATUS_SEND")
    private String statusSend;

    @Column(name = "STATUS_PRODUKTION")
    private String statusProduktion;

    @Column(name = "MEDDELELSE_ID")
    private Long meddelelseId;

    @Column(name = "MEDDELELSE_FEJL_KODE")
    private String meddelelseFejlKode;

    @Lob
    @Column(name = "MEDDELELSE_FEJL_TEKST")
    private String meddelelseFejlTekst;

    @OneToMany(mappedBy = "brev", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Collection<GenereretBrevEntity> genereretBrevs;

    public BrevEntity() {
        genereretBrevs = new LinkedList<>();
    }

    @PreUpdate
    void onPersist() {
        this.setDatoOpdateret(TimeTravelDateProvider.getInstance().getDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBrevId() {
        return brevId;
    }

    public void setBrevId(String brevId) {
        this.brevId = brevId;
    }

    public Date getDatoOprettelse() {
        return datoOprettelse;
    }

    public void setDatoOprettelse(Date datoOprettelse) {
        this.datoOprettelse = datoOprettelse;
    }

    public Date getDatoAogDStatusKald() {
        return datoAogDStatusKald;
    }

    public void setDatoAogDStatusKald(Date datoAogDStatusKald) {
        this.datoAogDStatusKald = datoAogDStatusKald;
    }

    public Date getDatoOpdateret() {
        return datoOpdateret;
    }

    public void setDatoOpdateret(Date datoOpdateret) {
        this.datoOpdateret = datoOpdateret;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getStatusSend() {
        return statusSend;
    }

    public void setStatusSend(String statusSend) {
        this.statusSend = statusSend;
    }

    public String getStatusProduktion() {
        return statusProduktion;
    }

    public void setStatusProduktion(String statusProduktion) {
        this.statusProduktion = statusProduktion;
    }

    public Long getMeddelelseId() {
        return meddelelseId;
    }

    public void setMeddelelseId(Long meddelelseId) {
        this.meddelelseId = meddelelseId;
    }

    public String getMeddelelseFejlKode() {
        return meddelelseFejlKode;
    }

    public void setMeddelelseFejlKode(String meddelelseFejlKode) {
        this.meddelelseFejlKode = meddelelseFejlKode;
    }

    public String getMeddelelseFejlTekst() {
        return meddelelseFejlTekst;
    }

    public void setMeddelelseFejlTekst(String meddelelseFejlTekst) {
        this.meddelelseFejlTekst = meddelelseFejlTekst;
    }

    public Collection<GenereretBrevEntity> getGenereretBrevs() {
        return genereretBrevs;
    }

    public void setGenereretBrevs(Collection<GenereretBrevEntity> genereretBrevs) {
        this.genereretBrevs = genereretBrevs;
    }

    public void addGenereretBrev(GenereretBrevEntity genereretBrev) {
        genereretBrevs.add(genereretBrev);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("version", version)
                .append("batchId", batchId)
                .append("brevId", brevId)
                .append("datoOprettelse", datoOprettelse)
                .append("datoAogDStatusKald", datoAogDStatusKald)
                .append("datoOpdateret", datoOpdateret)
                .append("statusSend", statusSend)
                .append("statusProduktion", statusProduktion)
                .append("meddelelseId", meddelelseId)
                .append("meddelelseFejlKode", meddelelseFejlKode)
                .append("meddelelseFejlTekst", meddelelseFejlTekst)
                .toString();
    }
}
