package dk.rim.is.ic.inttests.timesimulation;

import dk.rim.is.ic.inttests.Property;

import static dk.rim.is.common.CommonURLs.TIMESIMULATION.CLEAR_DATE;
import static dk.rim.is.common.CommonURLs.TIMESIMULATION.GET_DATE;
import static dk.rim.is.common.CommonURLs.TIMESIMULATION.ROOT;
import static dk.rim.is.common.CommonURLs.TIMESIMULATION.SET_DATE;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;

public class TimeSimulationEndpoints {
    private String getDateUrl;
    private String setDateUrl;
    private String clearDateUrl;

    private TimeSimulationEndpoints(String getDateUrl, String setDateUrl, String clearDateUrl) {
        this.getDateUrl = getDateUrl;
        this.setDateUrl = setDateUrl;
        this.clearDateUrl = clearDateUrl;
    }

    public static TimeSimulationEndpoints createEndpoints(Property protocol, Property host, Property port) {
        String getDateUrl = toUrl(protocol.load(), host.load(), port.load(),
                ROOT + GET_DATE);
        String setDateUrl = toUrl(protocol.load(), host.load(), port.load(),
                ROOT + SET_DATE);
        String clearDateUrl = toUrl(protocol.load(), host.load(), port.load(),
                ROOT + CLEAR_DATE);
        return new TimeSimulationEndpoints(getDateUrl, setDateUrl, clearDateUrl);
    }

    public String getGetDateUrl() {
        return getDateUrl;
    }

    public String getSetDateUrl() {
        return setDateUrl;
    }

    public String getClearDateUrl() {
        return clearDateUrl;
    }
}
