package dk.rim.is.abt.nymf.entity;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by mlo on 29-08-2017.
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
