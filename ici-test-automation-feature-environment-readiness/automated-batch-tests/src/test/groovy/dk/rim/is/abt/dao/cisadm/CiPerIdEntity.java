package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by micw on 07-06-2017.
 */
@Entity
@Table(name = "CI_PER_ID", schema = "CISADM", catalog = "")
@IdClass(CiPerIdEntityPK.class)
public class CiPerIdEntity {
    private String perId;
    private String idTypeCd;
    private String perIdNbr;
    private String primSw;
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
    @Column(name = "ID_TYPE_CD", nullable = false, length = 8)
    public String getIdTypeCd() {
        return idTypeCd;
    }

    public void setIdTypeCd(String idTypeCd) {
        this.idTypeCd = idTypeCd;
    }

    @Basic
    @Column(name = "PER_ID_NBR", nullable = false, length = 16)
    public String getPerIdNbr() {
        return perIdNbr;
    }

    public void setPerIdNbr(String perIdNbr) {
        this.perIdNbr = perIdNbr;
    }

    @Basic
    @Column(name = "PRIM_SW", nullable = false, length = 1)
    public String getPrimSw() {
        return primSw;
    }

    public void setPrimSw(String primSw) {
        this.primSw = primSw;
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

        CiPerIdEntity that = (CiPerIdEntity) o;

        if (version != that.version) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (idTypeCd != null ? !idTypeCd.equals(that.idTypeCd) : that.idTypeCd != null) return false;
        if (perIdNbr != null ? !perIdNbr.equals(that.perIdNbr) : that.perIdNbr != null) return false;
        if (primSw != null ? !primSw.equals(that.primSw) : that.primSw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (idTypeCd != null ? idTypeCd.hashCode() : 0);
        result = 31 * result + (perIdNbr != null ? perIdNbr.hashCode() : 0);
        result = 31 * result + (primSw != null ? primSw.hashCode() : 0);
        result = 31 * result + (int) version;
        return result;
    }
}
