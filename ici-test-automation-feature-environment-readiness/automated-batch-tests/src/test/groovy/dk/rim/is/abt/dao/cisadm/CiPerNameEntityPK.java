package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by micw on 09-06-2017.
 */
public class CiPerNameEntityPK implements Serializable {
    private String perId;
    private byte seqNum;

    @Column(name = "PER_ID", nullable = false, length = 10)
    @Id
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Column(name = "SEQ_NUM", nullable = false, precision = 0)
    @Id
    public byte getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(byte seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerNameEntityPK that = (CiPerNameEntityPK) o;

        if (seqNum != that.seqNum) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (int) seqNum;
        return result;
    }
}
