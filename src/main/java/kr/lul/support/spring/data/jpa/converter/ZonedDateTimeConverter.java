package kr.lul.support.spring.data.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String convertToDatabaseColumn(ZonedDateTime attribute) {
        return null == attribute
                ? null
                : FORMATTER.format(attribute);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        return null == dbData
                ? null
                : ZonedDateTime.parse(dbData, FORMATTER);
    }
}
