package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by micw on 07-06-2017.
 */
@Entity
@Table(name = "CI_PER", schema = "CISADM", catalog = "")
public class CiPerEntity {
    private String perId;
    private String languageCd;
    private String perOrBusFlg;
    private String lsSlFlg;
    private String lsSlDescr;
    private String emailid;
    private String ovrdMailName1;
    private String ovrdMailName2;
    private String ovrdMailName3;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String num1;
    private String num2;
    private String county;
    private String postal;
    private String houseType;
    private String geoCode;
    private String inCityLimit;
    private String state;
    private String country;
    private short version;
    private String recvMktgInfoFlg;
    private String webPasswd;
    private String webPwdHintFlg;
    private String webPasswdAns;
    private String c1PerDataArea;
    private String perTypeCd;

    @Id
    @Column(name = "PER_ID", nullable = false, length = 10)
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Basic
    @Column(name = "LANGUAGE_CD", nullable = false, length = 3)
    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }

    @Basic
    @Column(name = "PER_OR_BUS_FLG", nullable = false, length = 2)
    public String getPerOrBusFlg() {
        return perOrBusFlg;
    }

    public void setPerOrBusFlg(String perOrBusFlg) {
        this.perOrBusFlg = perOrBusFlg;
    }

    @Basic
    @Column(name = "LS_SL_FLG", nullable = false, length = 2)
    public String getLsSlFlg() {
        return lsSlFlg;
    }

    public void setLsSlFlg(String lsSlFlg) {
        this.lsSlFlg = lsSlFlg;
    }

    @Basic
    @Column(name = "LS_SL_DESCR", nullable = false, length = 254)
    public String getLsSlDescr() {
        return lsSlDescr;
    }

    public void setLsSlDescr(String lsSlDescr) {
        this.lsSlDescr = lsSlDescr;
    }

    @Basic
    @Column(name = "EMAILID", nullable = false, length = 254)
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    @Basic
    @Column(name = "OVRD_MAIL_NAME1", nullable = false, length = 254)
    public String getOvrdMailName1() {
        return ovrdMailName1;
    }

    public void setOvrdMailName1(String ovrdMailName1) {
        this.ovrdMailName1 = ovrdMailName1;
    }

    @Basic
    @Column(name = "OVRD_MAIL_NAME2", nullable = false, length = 254)
    public String getOvrdMailName2() {
        return ovrdMailName2;
    }

    public void setOvrdMailName2(String ovrdMailName2) {
        this.ovrdMailName2 = ovrdMailName2;
    }

    @Basic
    @Column(name = "OVRD_MAIL_NAME3", nullable = false, length = 254)
    public String getOvrdMailName3() {
        return ovrdMailName3;
    }

    public void setOvrdMailName3(String ovrdMailName3) {
        this.ovrdMailName3 = ovrdMailName3;
    }

    @Basic
    @Column(name = "ADDRESS1", nullable = false, length = 254)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "ADDRESS2", nullable = false, length = 254)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "ADDRESS3", nullable = false, length = 254)
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "ADDRESS4", nullable = false, length = 254)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Basic
    @Column(name = "CITY", nullable = false, length = 90)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "NUM1", nullable = false, length = 6)
    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    @Basic
    @Column(name = "NUM2", nullable = false, length = 4)
    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Basic
    @Column(name = "COUNTY", nullable = false, length = 90)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "POSTAL", nullable = false, length = 12)
    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Basic
    @Column(name = "HOUSE_TYPE", nullable = false, length = 2)
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "GEO_CODE", nullable = false, length = 11)
    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }

    @Basic
    @Column(name = "IN_CITY_LIMIT", nullable = false, length = 1)
    public String getInCityLimit() {
        return inCityLimit;
    }

    public void setInCityLimit(String inCityLimit) {
        this.inCityLimit = inCityLimit;
    }

    @Basic
    @Column(name = "STATE", nullable = false, length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "COUNTRY", nullable = false, length = 3)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
    @Column(name = "RECV_MKTG_INFO_FLG", nullable = false, length = 4)
    public String getRecvMktgInfoFlg() {
        return recvMktgInfoFlg;
    }

    public void setRecvMktgInfoFlg(String recvMktgInfoFlg) {
        this.recvMktgInfoFlg = recvMktgInfoFlg;
    }

    @Basic
    @Column(name = "WEB_PASSWD", nullable = false, length = 30)
    public String getWebPasswd() {
        return webPasswd;
    }

    public void setWebPasswd(String webPasswd) {
        this.webPasswd = webPasswd;
    }

    @Basic
    @Column(name = "WEB_PWD_HINT_FLG", nullable = false, length = 4)
    public String getWebPwdHintFlg() {
        return webPwdHintFlg;
    }

    public void setWebPwdHintFlg(String webPwdHintFlg) {
        this.webPwdHintFlg = webPwdHintFlg;
    }

    @Basic
    @Column(name = "WEB_PASSWD_ANS", nullable = false, length = 60)
    public String getWebPasswdAns() {
        return webPasswdAns;
    }

    public void setWebPasswdAns(String webPasswdAns) {
        this.webPasswdAns = webPasswdAns;
    }

    @Basic
    @Column(name = "C1_PER_DATA_AREA", nullable = true)
    public String getC1PerDataArea() {
        return c1PerDataArea;
    }

    public void setC1PerDataArea(String c1PerDataArea) {
        this.c1PerDataArea = c1PerDataArea;
    }

    @Basic
    @Column(name = "PER_TYPE_CD", nullable = false, length = 12)
    public String getPerTypeCd() {
        return perTypeCd;
    }

    public void setPerTypeCd(String perTypeCd) {
        this.perTypeCd = perTypeCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerEntity that = (CiPerEntity) o;

        if (version != that.version) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;
        if (languageCd != null ? !languageCd.equals(that.languageCd) : that.languageCd != null) return false;
        if (perOrBusFlg != null ? !perOrBusFlg.equals(that.perOrBusFlg) : that.perOrBusFlg != null) return false;
        if (lsSlFlg != null ? !lsSlFlg.equals(that.lsSlFlg) : that.lsSlFlg != null) return false;
        if (lsSlDescr != null ? !lsSlDescr.equals(that.lsSlDescr) : that.lsSlDescr != null) return false;
        if (emailid != null ? !emailid.equals(that.emailid) : that.emailid != null) return false;
        if (ovrdMailName1 != null ? !ovrdMailName1.equals(that.ovrdMailName1) : that.ovrdMailName1 != null)
            return false;
        if (ovrdMailName2 != null ? !ovrdMailName2.equals(that.ovrdMailName2) : that.ovrdMailName2 != null)
            return false;
        if (ovrdMailName3 != null ? !ovrdMailName3.equals(that.ovrdMailName3) : that.ovrdMailName3 != null)
            return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (address4 != null ? !address4.equals(that.address4) : that.address4 != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (num1 != null ? !num1.equals(that.num1) : that.num1 != null) return false;
        if (num2 != null ? !num2.equals(that.num2) : that.num2 != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (postal != null ? !postal.equals(that.postal) : that.postal != null) return false;
        if (houseType != null ? !houseType.equals(that.houseType) : that.houseType != null) return false;
        if (geoCode != null ? !geoCode.equals(that.geoCode) : that.geoCode != null) return false;
        if (inCityLimit != null ? !inCityLimit.equals(that.inCityLimit) : that.inCityLimit != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (recvMktgInfoFlg != null ? !recvMktgInfoFlg.equals(that.recvMktgInfoFlg) : that.recvMktgInfoFlg != null)
            return false;
        if (webPasswd != null ? !webPasswd.equals(that.webPasswd) : that.webPasswd != null) return false;
        if (webPwdHintFlg != null ? !webPwdHintFlg.equals(that.webPwdHintFlg) : that.webPwdHintFlg != null)
            return false;
        if (webPasswdAns != null ? !webPasswdAns.equals(that.webPasswdAns) : that.webPasswdAns != null) return false;
        if (c1PerDataArea != null ? !c1PerDataArea.equals(that.c1PerDataArea) : that.c1PerDataArea != null)
            return false;
        if (perTypeCd != null ? !perTypeCd.equals(that.perTypeCd) : that.perTypeCd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (languageCd != null ? languageCd.hashCode() : 0);
        result = 31 * result + (perOrBusFlg != null ? perOrBusFlg.hashCode() : 0);
        result = 31 * result + (lsSlFlg != null ? lsSlFlg.hashCode() : 0);
        result = 31 * result + (lsSlDescr != null ? lsSlDescr.hashCode() : 0);
        result = 31 * result + (emailid != null ? emailid.hashCode() : 0);
        result = 31 * result + (ovrdMailName1 != null ? ovrdMailName1.hashCode() : 0);
        result = 31 * result + (ovrdMailName2 != null ? ovrdMailName2.hashCode() : 0);
        result = 31 * result + (ovrdMailName3 != null ? ovrdMailName3.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (address4 != null ? address4.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (num1 != null ? num1.hashCode() : 0);
        result = 31 * result + (num2 != null ? num2.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (postal != null ? postal.hashCode() : 0);
        result = 31 * result + (houseType != null ? houseType.hashCode() : 0);
        result = 31 * result + (geoCode != null ? geoCode.hashCode() : 0);
        result = 31 * result + (inCityLimit != null ? inCityLimit.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (int) version;
        result = 31 * result + (recvMktgInfoFlg != null ? recvMktgInfoFlg.hashCode() : 0);
        result = 31 * result + (webPasswd != null ? webPasswd.hashCode() : 0);
        result = 31 * result + (webPwdHintFlg != null ? webPwdHintFlg.hashCode() : 0);
        result = 31 * result + (webPasswdAns != null ? webPasswdAns.hashCode() : 0);
        result = 31 * result + (c1PerDataArea != null ? c1PerDataArea.hashCode() : 0);
        result = 31 * result + (perTypeCd != null ? perTypeCd.hashCode() : 0);
        return result;
    }
}
