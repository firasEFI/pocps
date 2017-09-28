package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by micw on 08-06-2017.
 */
@Entity
@Table(name = "CI_PER_K", schema = "CISADM", catalog = "")
@IdClass(CiPerKEntityPK.class)
public class CiPerKEntity {
    private String perId;
    private long envId;

    @Id
    @Column(name = "PER_ID", nullable = false, length = 10)
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Id
    @Column(name = "ENV_ID", nullable = false, precision = 0)
    public long getEnvId() {
        return envId;
    }

    public void setEnvId(long envId) {
        this.envId = envId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CiPerKEntity that = (CiPerKEntity) o;

        if (envId != that.envId) return false;
        if (perId != null ? !perId.equals(that.perId) : that.perId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = perId != null ? perId.hashCode() : 0;
        result = 31 * result + (int) (envId ^ (envId >>> 32));
        return result;
    }
}
