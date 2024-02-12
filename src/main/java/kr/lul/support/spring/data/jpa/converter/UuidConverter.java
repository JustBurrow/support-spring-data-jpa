package kr.lul.support.spring.data.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

/**
 * {@link UUID} 필드 변환.
 *
 * @see UUID
 */
@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID attribute) {
        return null == attribute
                ? null
                : attribute.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        return null == dbData
                ? null
                : UUID.fromString(dbData);
    }
}
