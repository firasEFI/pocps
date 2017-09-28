package dk.rim.is.ic.inttests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Url utility class.
 */
public class UrlUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UrlUtils.class);

    public static String toUrl(String protocol, String host, String port, String path) {
        try {
            String normalizedPath = path.startsWith("/") || path.isEmpty() ? path : "/" + path;
            URL url = new URL(protocol, host, Integer.parseInt(port), normalizedPath);
            String urlString = url.toExternalForm();
            LOG.info("Using URL: " + urlString);
            return urlString;
        } catch (MalformedURLException | NumberFormatException e) {
            throw new RuntimeException("It occured " + e.getClass().getSimpleName() +" during creating URL with " +
                    "protocol="+protocol+", " +
                    "host="+host+", " +
                    "port="+port+", " +
                    "path="+path, e);
        }
    }

    public static String toUrl(String protocol, String host, String port, Property path) {
        return toUrl(protocol, host, port, path.load());
    }

    public static String toUrl(Property protocol, Property host, Property port, Property path) {
        return toUrl(protocol.load(), host.load(), port.load(), path.load());
    }

    public static String toUrl(String protocol, Property host, Property port, Property path) {
        return toUrl(protocol, host.load(), port.load(), path.load());
    }

    public static String toUrl(String protocol, Property host, Property port, String path) {
        return toUrl(protocol, host.load(), port.load(), path);
    }

    public static String toUrl(String protocol, Property host, Property port) {
        return toUrl(protocol, host.load(), port.load(), "");
    }
}
