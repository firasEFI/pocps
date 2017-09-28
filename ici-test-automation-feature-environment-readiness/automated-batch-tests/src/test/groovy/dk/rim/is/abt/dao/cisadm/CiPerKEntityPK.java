package dk.rim.is.abt.dao.cisadm;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by micw on 08-06-2017.
 */
public class CiPerKEntityPK implements Serializable {
    private String perId;
    private long envId;

    @Column(name = "PER_ID", nullable = false, length = 10)
    @Id
    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Column(name = "ENV_ID", nullable = false, precision = 0)
    @Id
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

        CiPerKEntityPK that = (CiPerKEntityPK) o;

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
