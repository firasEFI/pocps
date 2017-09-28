package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Time;

/**
 * Created by micw on 09-06-2017.
 */
@Entity
@Table(name = "CI_PER_ADDR", schema = "CISADM", catalog = "")
@IdClass(CiPerAddrEntityPK.class)
public class CiPerAddrEntity {
    private String perId;
    private byte seqNum;
    private String addressId;
    private String addressTypeXflg;
    private Time startDt;
    private Time endDt;
    private String seasonStartMmdd;
    private String seasonEndMmdd;
    private String addressPrioFlg;
    private String deliverableFlg;
    private short version;

    @Id
    @Column(name = "PER_ID", nullable = false, length = 10)
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Id
    @Column(name = "SEQ_NUM", nullable = false, precision = 0)
    public byte getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(byte seqNum) {
        this.seqNum = seqNum;
    }

    @Basic
    @Column(name = "ADDRESS_ID", nullable = false, length = 14)
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "ADDRESS_TYPE_XFLG", nullable = false, length = 30)
    public String getAddressTypeXflg() {
        return addressTypeXflg;
    }

    public void setAddressTypeXflg(String addressTypeXflg) {
        this.addressTypeXflg = addressTypeXflg;
    }

    @Basic
    @Column(name = "START_DT", nullable = false)
    public Time getStartDt() {
        return startDt;
    }

    public void setStartDt(Time startDt) {
        this.startDt = startDt;
    }

    @Basic
    @Column(name = "END_DT", nullable = true)
    public Time getEndDt() {
        return endDt;
    }

    public void setEndDt(Time endDt) {
        this.endDt = endDt;
    }

    @Basic
    @Column(name = "SEASON_START_MMDD", nullable = true, length = 4)
    public String getSeasonStartMmdd() {
        return seasonStartMmdd;
    }

    public void setSeasonStartMmdd(String seasonStartMmdd) {
        this.seasonStartMmdd = seasonStartMmdd;
    }

    @Basic
    @Column(name = "SEASON_END_MMDD", nullable = true, length = 4)
    public String getSeasonEndMmdd() {
        return seasonEndMmdd;
    }

    public void setSeasonEndMmdd(String seasonEndMmdd) {
        this.seasonEndMmdd = seasonEndMmdd;
    }

    @Basic
    @Column(name = "ADDRESS_PRIO_FLG", nullable = true, length = 4)
    public String getAddressPrioFlg() {
        return addressPrioFlg;
    }

    public void setAddressPrioFlg(String addressPrioFlg) {
        this.addressPrioFlg = addressPrioFlg;
    }

    @Basic
    @Column(name = "DELIVERABLE_FLG", nullable = false, length = 4)
    public String getDeliverableFlg() {
        return deliverableFlg;
    }

    public void setDeliverableFlg(String deliverableFlg) {
        this.deliverableFlg = deliverableFlg;
    }

    @Basic
    @Column(name = "VERSION", nullable = false, precision = 0)
    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerAddrEntity that = (CiPerAddrEntity) o;

        if (seqNum != that.seqNum) return false;
        if (version != that.version) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (addressTypeXflg != null ? !addressTypeXflg.equals(that.addressTypeXflg) : that.addressTypeXflg != null)
            return false;
        if (startDt != null ? !startDt.equals(that.startDt) : that.startDt != null) return false;
        if (endDt != null ? !endDt.equals(that.endDt) : that.endDt != null) return false;
        if (seasonStartMmdd != null ? !seasonStartMmdd.equals(that.seasonStartMmdd) : that.seasonStartMmdd != null)
            return false;
        if (seasonEndMmdd != null ? !seasonEndMmdd.equals(that.seasonEndMmdd) : that.seasonEndMmdd != null)
            return false;
        if (addressPrioFlg != null ? !addressPrioFlg.equals(that.addressPrioFlg) : that.addressPrioFlg != null)
            return false;
        if (deliverableFlg != null ? !deliverableFlg.equals(that.deliverableFlg) : that.deliverableFlg != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (int) seqNum;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (addressTypeXflg != null ? addressTypeXflg.hashCode() : 0);
        result = 31 * result + (startDt != null ? startDt.hashCode() : 0);
        result = 31 * result + (endDt != null ? endDt.hashCode() : 0);
        result = 31 * result + (seasonStartMmdd != null ? seasonStartMmdd.hashCode() : 0);
        result = 31 * result + (seasonEndMmdd != null ? seasonEndMmdd.hashCode() : 0);
        result = 31 * result + (addressPrioFlg != null ? addressPrioFlg.hashCode() : 0);
        result = 31 * result + (deliverableFlg != null ? deliverableFlg.hashCode() : 0);
        result = 31 * result + (int) version;
        return result;
    }
}
