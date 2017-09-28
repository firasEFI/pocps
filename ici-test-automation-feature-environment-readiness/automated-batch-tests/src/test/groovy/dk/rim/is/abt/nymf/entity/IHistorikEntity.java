package dk.rim.is.abt.nymf.entity;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA. Please write your own text here.
 * User: brj@netcompany.com
 * Date: 2017-03-23
 */
public interface IHistorikEntity extends IEntity {
    TransaktionHistorik getTransaktionHistorik();

    void setTransaktionHistorik(TransaktionHistorik transaktionHistorik);

    Long getSystemIndberetterId();

    void setSystemIndberetterId(Long systemIndberetterId);

    String getResult();

    void setResult(String result);

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timestamp);
}
