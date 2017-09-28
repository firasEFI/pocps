package dk.rim.is.ic.inttests;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

/**
 * Content types.
 */
public interface ContentTypes {

    /** application/xml; charset=UTF-8 */
    String APPLICATION_XML_UTF_8 = ContentType.APPLICATION_XML.withCharset(Consts.UTF_8).toString();
    /** application/json */
    String APPLICATION_JSON = ContentType.APPLICATION_JSON.getMimeType();
    /** application/json; charset=UTF-8 */
    String APPLICATION_JSON_UTF_8 = ContentType.APPLICATION_JSON.withCharset(Consts.UTF_8).toString();
    /** text/plain */
    String TEXT_PLAIN = ContentType.TEXT_PLAIN.getMimeType();

}
