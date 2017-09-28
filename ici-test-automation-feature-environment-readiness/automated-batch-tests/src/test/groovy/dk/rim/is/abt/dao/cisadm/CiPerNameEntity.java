package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by micw on 09-06-2017.
 */
@Entity
@Table(name = "CI_PER_NAME", schema = "CISADM", catalog = "")
@IdClass(CiPerNameEntityPK.class)
public class CiPerNameEntity {
    private String perId;
    private byte seqNum;
    private String entityName;
    private String nameTypeFlg;
    private short version;
    private String primNameSw;
    private String entityNameUpr;
    private String c1FirstName;
    private String middleName;
    private String c1LastName;
    private String namePrefixFlg;
    private String nameSuffix;
    private String c1FirstNameUpr;
    private String c1LastNameUpr;

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
    @Column(name = "ENTITY_NAME", nullable = false, length = 254)
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Basic
    @Column(name = "NAME_TYPE_FLG", nullable = false, length = 4)
    public String getNameTypeFlg() {
        return nameTypeFlg;
    }

    public void setNameTypeFlg(String nameTypeFlg) {
        this.nameTypeFlg = nameTypeFlg;
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
    @Column(name = "PRIM_NAME_SW", nullable = false, length = 1)
    public String getPrimNameSw() {
        return primNameSw;
    }

    public void setPrimNameSw(String primNameSw) {
        this.primNameSw = primNameSw;
    }

    @Basic
    @Column(name = "ENTITY_NAME_UPR", nullable = false, length = 254)
    public String getEntityNameUpr() {
        return entityNameUpr;
    }

    public void setEntityNameUpr(String entityNameUpr) {
        this.entityNameUpr = entityNameUpr;
    }

    @Basic
    @Column(name = "C1_FIRST_NAME", nullable = true, length = 50)
    public String getC1FirstName() {
        return c1FirstName;
    }

    public void setC1FirstName(String c1FirstName) {
        this.c1FirstName = c1FirstName;
    }

    @Basic
    @Column(name = "MIDDLE_NAME", nullable = true, length = 50)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "C1_LAST_NAME", nullable = true, length = 50)
    public String getC1LastName() {
        return c1LastName;
    }

    public void setC1LastName(String c1LastName) {
        this.c1LastName = c1LastName;
    }

    @Basic
    @Column(name = "NAME_PREFIX_FLG", nullable = true, length = 4)
    public String getNamePrefixFlg() {
        return namePrefixFlg;
    }

    public void setNamePrefixFlg(String namePrefixFlg) {
        this.namePrefixFlg = namePrefixFlg;
    }

    @Basic
    @Column(name = "NAME_SUFFIX", nullable = true, length = 50)
    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    @Basic
    @Column(name = "C1_FIRST_NAME_UPR", nullable = true, length = 50)
    public String getC1FirstNameUpr() {
        return c1FirstNameUpr;
    }

    public void setC1FirstNameUpr(String c1FirstNameUpr) {
        this.c1FirstNameUpr = c1FirstNameUpr;
    }

    @Basic
    @Column(name = "C1_LAST_NAME_UPR", nullable = true, length = 50)
    public String getC1LastNameUpr() {
        return c1LastNameUpr;
    }

    public void setC1LastNameUpr(String c1LastNameUpr) {
        this.c1LastNameUpr = c1LastNameUpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerNameEntity that = (CiPerNameEntity) o;

        if (seqNum != that.seqNum) return false;
        if (version != that.version) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (entityName != null ? !entityName.equals(that.entityName) : that.entityName != null) return false;
        if (nameTypeFlg != null ? !nameTypeFlg.equals(that.nameTypeFlg) : that.nameTypeFlg != null) return false;
        if (primNameSw != null ? !primNameSw.equals(that.primNameSw) : that.primNameSw != null) return false;
        if (entityNameUpr != null ? !entityNameUpr.equals(that.entityNameUpr) : that.entityNameUpr != null)
            return false;
        if (c1FirstName != null ? !c1FirstName.equals(that.c1FirstName) : that.c1FirstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (c1LastName != null ? !c1LastName.equals(that.c1LastName) : that.c1LastName != null) return false;
        if (namePrefixFlg != null ? !namePrefixFlg.equals(that.namePrefixFlg) : that.namePrefixFlg != null)
            return false;
        if (nameSuffix != null ? !nameSuffix.equals(that.nameSuffix) : that.nameSuffix != null) return false;
        if (c1FirstNameUpr != null ? !c1FirstNameUpr.equals(that.c1FirstNameUpr) : that.c1FirstNameUpr != null)
            return false;
        if (c1LastNameUpr != null ? !c1LastNameUpr.equals(that.c1LastNameUpr) : that.c1LastNameUpr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (int) seqNum;
        result = 31 * result + (entityName != null ? entityName.hashCode() : 0);
        result = 31 * result + (nameTypeFlg != null ? nameTypeFlg.hashCode() : 0);
        result = 31 * result + (int) version;
        result = 31 * result + (primNameSw != null ? primNameSw.hashCode() : 0);
        result = 31 * result + (entityNameUpr != null ? entityNameUpr.hashCode() : 0);
        result = 31 * result + (c1FirstName != null ? c1FirstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (c1LastName != null ? c1LastName.hashCode() : 0);
        result = 31 * result + (namePrefixFlg != null ? namePrefixFlg.hashCode() : 0);
        result = 31 * result + (nameSuffix != null ? nameSuffix.hashCode() : 0);
        result = 31 * result + (c1FirstNameUpr != null ? c1FirstNameUpr.hashCode() : 0);
        result = 31 * result + (c1LastNameUpr != null ? c1LastNameUpr.hashCode() : 0);
        return result;
    }
}
