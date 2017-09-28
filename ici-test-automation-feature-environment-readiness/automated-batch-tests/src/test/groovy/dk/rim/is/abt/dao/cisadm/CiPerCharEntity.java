package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by micw on 08-06-2017.
 */
@Entity
@Table(name = "CI_PER_CHAR", schema = "CISADM", catalog = "")
@IdClass(CiPerCharEntityPK.class)
public class CiPerCharEntity {
    private String perId;
    private String charTypeCd;
    private String charVal;
    private Date effdt;
    private String adhocCharVal;
    private short version;
    private String charValFk1;
    private String charValFk2;
    private String charValFk3;
    private String charValFk4;
    private String charValFk5;
    private String srchCharVal;

    @Id
    @Column(name = "PER_ID", nullable = false, length = 10)
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Id
    @Column(name = "CHAR_TYPE_CD", nullable = false, length = 8)
    public String getCharTypeCd() {
        return charTypeCd;
    }

    public void setCharTypeCd(String charTypeCd) {
        this.charTypeCd = charTypeCd;
    }

    @Basic
    @Column(name = "CHAR_VAL", nullable = false, length = 16)
    public String getCharVal() {
        return charVal;
    }

    public void setCharVal(String charVal) {
        this.charVal = charVal;
    }

    @Id
    @Column(name = "EFFDT", nullable = false)
    public Date getEffdt() {
        return effdt;
    }

    public void setEffdt(Date effdt) {
        this.effdt = effdt;
    }

    @Basic
    @Column(name = "ADHOC_CHAR_VAL", nullable = false, length = 254)
    public String getAdhocCharVal() {
        return adhocCharVal;
    }

    public void setAdhocCharVal(String adhocCharVal) {
        this.adhocCharVal = adhocCharVal;
    }

    @Basic
    @Column(name = "VERSION", nullable = false, precision = 0)
    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    @Basic
    @Column(name = "CHAR_VAL_FK1", nullable = false, length = 50)
    public String getCharValFk1() {
        return charValFk1;
    }

    public void setCharValFk1(String charValFk1) {
        this.charValFk1 = charValFk1;
    }

    @Basic
    @Column(name = "CHAR_VAL_FK2", nullable = false, length = 50)
    public String getCharValFk2() {
        return charValFk2;
    }

    public void setCharValFk2(String charValFk2) {
        this.charValFk2 = charValFk2;
    }

    @Basic
    @Column(name = "CHAR_VAL_FK3", nullable = false, length = 50)
    public String getCharValFk3() {
        return charValFk3;
    }

    public void setCharValFk3(String charValFk3) {
        this.charValFk3 = charValFk3;
    }

    @Basic
    @Column(name = "CHAR_VAL_FK4", nullable = false, length = 50)
    public String getCharValFk4() {
        return charValFk4;
    }

    public void setCharValFk4(String charValFk4) {
        this.charValFk4 = charValFk4;
    }

    @Basic
    @Column(name = "CHAR_VAL_FK5", nullable = false, length = 50)
    public String getCharValFk5() {
        return charValFk5;
    }

    public void setCharValFk5(String charValFk5) {
        this.charValFk5 = charValFk5;
    }

    @Basic
    @Column(name = "SRCH_CHAR_VAL", nullable = false, length = 50)
    public String getSrchCharVal() {
        return srchCharVal;
    }

    public void setSrchCharVal(String srchCharVal) {
        this.srchCharVal = srchCharVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerCharEntity that = (CiPerCharEntity) o;

        if (version != that.version) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (charTypeCd != null ? !charTypeCd.equals(that.charTypeCd) : that.charTypeCd != null) return false;
        if (charVal != null ? !charVal.equals(that.charVal) : that.charVal != null) return false;
        if (effdt != null ? !effdt.equals(that.effdt) : that.effdt != null) return false;
        if (adhocCharVal != null ? !adhocCharVal.equals(that.adhocCharVal) : that.adhocCharVal != null) return false;
        if (charValFk1 != null ? !charValFk1.equals(that.charValFk1) : that.charValFk1 != null) return false;
        if (charValFk2 != null ? !charValFk2.equals(that.charValFk2) : that.charValFk2 != null) return false;
        if (charValFk3 != null ? !charValFk3.equals(that.charValFk3) : that.charValFk3 != null) return false;
        if (charValFk4 != null ? !charValFk4.equals(that.charValFk4) : that.charValFk4 != null) return false;
        if (charValFk5 != null ? !charValFk5.equals(that.charValFk5) : that.charValFk5 != null) return false;
        if (srchCharVal != null ? !srchCharVal.equals(that.srchCharVal) : that.srchCharVal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (charTypeCd != null ? charTypeCd.hashCode() : 0);
        result = 31 * result + (charVal != null ? charVal.hashCode() : 0);
        result = 31 * result + (effdt != null ? effdt.hashCode() : 0);
        result = 31 * result + (adhocCharVal != null ? adhocCharVal.hashCode() : 0);
        result = 31 * result + (int) version;
        result = 31 * result + (charValFk1 != null ? charValFk1.hashCode() : 0);
        result = 31 * result + (charValFk2 != null ? charValFk2.hashCode() : 0);
        result = 31 * result + (charValFk3 != null ? charValFk3.hashCode() : 0);
        result = 31 * result + (charValFk4 != null ? charValFk4.hashCode() : 0);
        result = 31 * result + (charValFk5 != null ? charValFk5.hashCode() : 0);
        result = 31 * result + (srchCharVal != null ? srchCharVal.hashCode() : 0);
        return result;
    }
}
