package kr.lul.support.spring.data.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, String> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public String convertToDatabaseColumn(OffsetDateTime attribute) {
        return null == attribute
                ? null
                : attribute.format(FORMATTER);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbData) {
        return null == dbData
                ? null
                : OffsetDateTime.parse(dbData, FORMATTER);
    }
}
