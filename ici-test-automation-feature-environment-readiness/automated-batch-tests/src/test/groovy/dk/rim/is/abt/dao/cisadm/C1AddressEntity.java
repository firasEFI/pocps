package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by micw on 09-06-2017.
 */
@Entity
@Table(name = "C1_ADDRESS", schema = "CISADM", catalog = "")
public class C1AddressEntity {
    private String addressId;
    private String busObjCd;
    private String num1;
    private String num2;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String county;
    private String city;
    private String state;
    private String postal;
    private String country;
    private String c1ActiveInactiveFlg;
    private String inactiveReasonXflg;
    private Long c1Longitude;
    private Long c1Latitude;
    private String address1Upr;
    private String cityUpr;
    private String stateUpr;
    private String postalUpr;
    private String boDataArea;
    private short version;

    @Id
    @Column(name = "ADDRESS_ID", nullable = false, length = 14)
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "BUS_OBJ_CD", nullable = false, length = 30)
    public String getBusObjCd() {
        return busObjCd;
    }

    public void setBusObjCd(String busObjCd) {
        this.busObjCd = busObjCd;
    }

    @Basic
    @Column(name = "NUM1", nullable = true, length = 6)
    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    @Basic
    @Column(name = "NUM2", nullable = true, length = 4)
    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
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
    @Column(name = "ADDRESS2", nullable = true, length = 254)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "ADDRESS3", nullable = true, length = 254)
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "ADDRESS4", nullable = true, length = 254)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Basic
    @Column(name = "COUNTY", nullable = true, length = 90)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "CITY", nullable = true, length = 90)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "POSTAL", nullable = true, length = 12)
    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Basic
    @Column(name = "COUNTRY", nullable = true, length = 3)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "C1_ACTIVE_INACTIVE_FLG", nullable = false, length = 4)
    public String getC1ActiveInactiveFlg() {
        return c1ActiveInactiveFlg;
    }

    public void setC1ActiveInactiveFlg(String c1ActiveInactiveFlg) {
        this.c1ActiveInactiveFlg = c1ActiveInactiveFlg;
    }

    @Basic
    @Column(name = "INACTIVE_REASON_XFLG", nullable = true, length = 30)
    public String getInactiveReasonXflg() {
        return inactiveReasonXflg;
    }

    public void setInactiveReasonXflg(String inactiveReasonXflg) {
        this.inactiveReasonXflg = inactiveReasonXflg;
    }

    @Basic
    @Column(name = "C1_LONGITUDE", nullable = true, precision = 6)
    public Long getC1Longitude() {
        return c1Longitude;
    }

    public void setC1Longitude(Long c1Longitude) {
        this.c1Longitude = c1Longitude;
    }

    @Basic
    @Column(name = "C1_LATITUDE", nullable = true, precision = 6)
    public Long getC1Latitude() {
        return c1Latitude;
    }

    public void setC1Latitude(Long c1Latitude) {
        this.c1Latitude = c1Latitude;
    }

    @Basic
    @Column(name = "ADDRESS1_UPR", nullable = true, length = 254)
    public String getAddress1Upr() {
        return address1Upr;
    }

    public void setAddress1Upr(String address1Upr) {
        this.address1Upr = address1Upr;
    }

    @Basic
    @Column(name = "CITY_UPR", nullable = true, length = 90)
    public String getCityUpr() {
        return cityUpr;
    }

    public void setCityUpr(String cityUpr) {
        this.cityUpr = cityUpr;
    }

    @Basic
    @Column(name = "STATE_UPR", nullable = true, length = 6)
    public String getStateUpr() {
        return stateUpr;
    }

    public void setStateUpr(String stateUpr) {
        this.stateUpr = stateUpr;
    }

    @Basic
    @Column(name = "POSTAL_UPR", nullable = true, length = 12)
    public String getPostalUpr() {
        return postalUpr;
    }

    public void setPostalUpr(String postalUpr) {
        this.postalUpr = postalUpr;
    }

    @Basic
    @Column(name = "BO_DATA_AREA", nullable = true)
    public String getBoDataArea() {
        return boDataArea;
    }

    public void setBoDataArea(String boDataArea) {
        this.boDataArea = boDataArea;
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

        C1AddressEntity that = (C1AddressEntity) o;

        if (version != that.version) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (busObjCd != null ? !busObjCd.equals(that.busObjCd) : that.busObjCd != null) return false;
        if (num1 != null ? !num1.equals(that.num1) : that.num1 != null) return false;
        if (num2 != null ? !num2.equals(that.num2) : that.num2 != null) return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (address4 != null ? !address4.equals(that.address4) : that.address4 != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (postal != null ? !postal.equals(that.postal) : that.postal != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (c1ActiveInactiveFlg != null ? !c1ActiveInactiveFlg.equals(that.c1ActiveInactiveFlg) : that.c1ActiveInactiveFlg != null)
            return false;
        if (inactiveReasonXflg != null ? !inactiveReasonXflg.equals(that.inactiveReasonXflg) : that.inactiveReasonXflg != null)
            return false;
        if (c1Longitude != null ? !c1Longitude.equals(that.c1Longitude) : that.c1Longitude != null) return false;
        if (c1Latitude != null ? !c1Latitude.equals(that.c1Latitude) : that.c1Latitude != null) return false;
        if (address1Upr != null ? !address1Upr.equals(that.address1Upr) : that.address1Upr != null) return false;
        if (cityUpr != null ? !cityUpr.equals(that.cityUpr) : that.cityUpr != null) return false;
        if (stateUpr != null ? !stateUpr.equals(that.stateUpr) : that.stateUpr != null) return false;
        if (postalUpr != null ? !postalUpr.equals(that.postalUpr) : that.postalUpr != null) return false;
        if (boDataArea != null ? !boDataArea.equals(that.boDataArea) : that.boDataArea != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (busObjCd != null ? busObjCd.hashCode() : 0);
        result = 31 * result + (num1 != null ? num1.hashCode() : 0);
        result = 31 * result + (num2 != null ? num2.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (address4 != null ? address4.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (postal != null ? postal.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (c1ActiveInactiveFlg != null ? c1ActiveInactiveFlg.hashCode() : 0);
        result = 31 * result + (inactiveReasonXflg != null ? inactiveReasonXflg.hashCode() : 0);
        result = 31 * result + (c1Longitude != null ? c1Longitude.hashCode() : 0);
        result = 31 * result + (c1Latitude != null ? c1Latitude.hashCode() : 0);
        result = 31 * result + (address1Upr != null ? address1Upr.hashCode() : 0);
        result = 31 * result + (cityUpr != null ? cityUpr.hashCode() : 0);
        result = 31 * result + (stateUpr != null ? stateUpr.hashCode() : 0);
        result = 31 * result + (postalUpr != null ? postalUpr.hashCode() : 0);
        result = 31 * result + (boDataArea != null ? boDataArea.hashCode() : 0);
        result = 31 * result + (int) version;
        return result;
    }
}
