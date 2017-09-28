package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by micw on 07-06-2017.
 */
public class CiPerIdEntityPK implements Serializable {
    private String perId;
    private String idTypeCd;

    @Column(name = "PER_ID", nullable = false, length = 10)
    @Id
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Column(name = "ID_TYPE_CD", nullable = false, length = 8)
    @Id
    public String getIdTypeCd() {
        return idTypeCd;
    }

    public void setIdTypeCd(String idTypeCd) {
        this.idTypeCd = idTypeCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerIdEntityPK that = (CiPerIdEntityPK) o;

        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (idTypeCd != null ? !idTypeCd.equals(that.idTypeCd) : that.idTypeCd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (idTypeCd != null ? idTypeCd.hashCode() : 0);
        return result;
    }
}
