package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by micw on 08-06-2017.
 */
public class CiPerCharEntityPK implements Serializable {
    private String perId;
    private String charTypeCd;
    private Date effdt;

    @Column(name = "PER_ID", nullable = false, length = 10)
    @Id
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Column(name = "CHAR_TYPE_CD", nullable = false, length = 8)
    @Id
    public String getCharTypeCd() {
        return charTypeCd;
    }

    public void setCharTypeCd(String charTypeCd) {
        this.charTypeCd = charTypeCd;
    }

    @Column(name = "EFFDT", nullable = false)
    @Id
    public Date getEffdt() {
        return effdt;
    }

    public void setEffdt(Date effdt) {
        this.effdt = effdt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerCharEntityPK that = (CiPerCharEntityPK) o;

        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (charTypeCd != null ? !charTypeCd.equals(that.charTypeCd) : that.charTypeCd != null) return false;
        if (effdt != null ? !effdt.equals(that.effdt) : that.effdt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (charTypeCd != null ? charTypeCd.hashCode() : 0);
        result = 31 * result + (effdt != null ? effdt.hashCode() : 0);
        return result;
    }
}
