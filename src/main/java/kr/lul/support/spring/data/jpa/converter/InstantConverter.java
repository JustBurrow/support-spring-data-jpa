package kr.lul.support.spring.data.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

/**
 * {@link Instant} - {@link Long} 상호 변환.
 * <p>
 * 왜 SQL로 데이터를 보려고 해? 데이터 조작은 애플리케이션에 맡기고 DB는 성능과 안전성에만 집중하세요.
 */
@Converter(autoApply = true)
public class InstantConverter implements AttributeConverter<Instant, Long> {
    @Override
    public Long convertToDatabaseColumn(Instant attribute) {
        return null == attribute
                ? null
                : attribute.toEpochMilli();
    }

    @Override
    public Instant convertToEntityAttribute(Long dbData) {
        return null == dbData
                ? null
                : Instant.ofEpochMilli(dbData);
    }
}
