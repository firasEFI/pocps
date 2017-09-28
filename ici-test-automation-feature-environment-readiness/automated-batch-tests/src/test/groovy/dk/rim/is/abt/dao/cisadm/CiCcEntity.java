package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * Created by micw on 02-06-2017.
 */
@Entity
@Table(name = "CI_CC", schema = "CISADM", catalog = "")
public class CiCcEntity {
    private String ccId;
    private String userId;
    private String perId;
    private LocalDateTime ccDttm;
    private String ccClCd;
    private String ccTypeCd;
    private String printLetterSw;
    private LocalDateTime letterPrintDttm;
    private String ltrTmplCd;
    private String descr254;
    private short version;
    private String batchCd;
    private int batchNbr;
    private String ccStatusFlg;
    private String c1CcDataArea;

    @Id
    @Column(name = "CC_ID", nullable = false, length = 10)
    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, length = 8)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "PER_ID", nullable = false, length = 10)
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Basic
    @Column(name = "CC_DTTM", nullable = false)
    public LocalDateTime getCcDttm() {
        return ccDttm;
    }

    public void setCcDttm(LocalDateTime ccDttm) {
        this.ccDttm = ccDttm;
    }

    @Basic
    @Column(name = "CC_CL_CD", nullable = false, length = 4)
    public String getCcClCd() {
        return ccClCd;
    }

    public void setCcClCd(String ccClCd) {
        this.ccClCd = ccClCd;
    }

    @Basic
    @Column(name = "CC_TYPE_CD", nullable = false, length = 12)
    public String getCcTypeCd() {
        return ccTypeCd;
    }

    public void setCcTypeCd(String ccTypeCd) {
        this.ccTypeCd = ccTypeCd;
    }

    @Basic
    @Column(name = "PRINT_LETTER_SW", nullable = false, length = 1)
    public String getPrintLetterSw() {
        return printLetterSw;
    }

    public void setPrintLetterSw(String printLetterSw) {
        this.printLetterSw = printLetterSw;
    }

    @Basic
    @Column(name = "LETTER_PRINT_DTTM", nullable = true)
    public LocalDateTime getLetterPrintDttm() {
        return letterPrintDttm;
    }

    public void setLetterPrintDttm(LocalDateTime letterPrintDttm) {
        this.letterPrintDttm = letterPrintDttm;
    }

    @Basic
    @Column(name = "LTR_TMPL_CD", nullable = false, length = 12)
    public String getLtrTmplCd() {
        return ltrTmplCd;
    }

    public void setLtrTmplCd(String ltrTmplCd) {
        this.ltrTmplCd = ltrTmplCd;
    }

    @Basic
    @Column(name = "DESCR254", nullable = false, length = 254)
    public String getDescr254() {
        return descr254;
    }

    public void setDescr254(String descr254) {
        this.descr254 = descr254;
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
    @Column(name = "BATCH_CD", nullable = false, length = 8)
    public String getBatchCd() {
        return batchCd;
    }

    public void setBatchCd(String batchCd) {
        this.batchCd = batchCd;
    }

    @Basic
    @Column(name = "BATCH_NBR", nullable = false, precision = 0)
    public int getBatchNbr() {
        return batchNbr;
    }

    public void setBatchNbr(int batchNbr) {
        this.batchNbr = batchNbr;
    }

    @Basic
    @Column(name = "CC_STATUS_FLG", nullable = false, length = 4)
    public String getCcStatusFlg() {
        return ccStatusFlg;
    }

    public void setCcStatusFlg(String ccStatusFlg) {
        this.ccStatusFlg = ccStatusFlg;
    }

    @Basic
    @Column(name = "C1_CC_DATA_AREA", nullable = true)
    public String getC1CcDataArea() {
        return c1CcDataArea;
    }

    public void setC1CcDataArea(String c1CcDataArea) {
        this.c1CcDataArea = c1CcDataArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiCcEntity that = (CiCcEntity) o;

        if (version != that.version) return false;
        if (batchNbr != that.batchNbr) return false;
        if (ccId != null ? !ccId.equals(that.ccId) : that.ccId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (ccDttm != null ? !ccDttm.equals(that.ccDttm) : that.ccDttm != null) return false;
        if (ccClCd != null ? !ccClCd.equals(that.ccClCd) : that.ccClCd != null) return false;
        if (ccTypeCd != null ? !ccTypeCd.equals(that.ccTypeCd) : that.ccTypeCd != null) return false;
        if (printLetterSw != null ? !printLetterSw.equals(that.printLetterSw) : that.printLetterSw != null)
            return false;
        if (letterPrintDttm != null ? !letterPrintDttm.equals(that.letterPrintDttm) : that.letterPrintDttm != null)
            return false;
        if (ltrTmplCd != null ? !ltrTmplCd.equals(that.ltrTmplCd) : that.ltrTmplCd != null) return false;
        if (descr254 != null ? !descr254.equals(that.descr254) : that.descr254 != null) return false;
        if (batchCd != null ? !batchCd.equals(that.batchCd) : that.batchCd != null) return false;
        if (ccStatusFlg != null ? !ccStatusFlg.equals(that.ccStatusFlg) : that.ccStatusFlg != null) return false;
        if (c1CcDataArea != null ? !c1CcDataArea.equals(that.c1CcDataArea) : that.c1CcDataArea != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ccId != null ? ccId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (perId != null ? perId.hashCode() : 0);
        result = 31 * result + (ccDttm != null ? ccDttm.hashCode() : 0);
        result = 31 * result + (ccClCd != null ? ccClCd.hashCode() : 0);
        result = 31 * result + (ccTypeCd != null ? ccTypeCd.hashCode() : 0);
        result = 31 * result + (printLetterSw != null ? printLetterSw.hashCode() : 0);
        result = 31 * result + (letterPrintDttm != null ? letterPrintDttm.hashCode() : 0);
        result = 31 * result + (ltrTmplCd != null ? ltrTmplCd.hashCode() : 0);
        result = 31 * result + (descr254 != null ? descr254.hashCode() : 0);
        result = 31 * result + (int) version;
        result = 31 * result + (batchCd != null ? batchCd.hashCode() : 0);
        result = 31 * result + batchNbr;
        result = 31 * result + (ccStatusFlg != null ? ccStatusFlg.hashCode() : 0);
        result = 31 * result + (c1CcDataArea != null ? c1CcDataArea.hashCode() : 0);
        return result;
    }
}
