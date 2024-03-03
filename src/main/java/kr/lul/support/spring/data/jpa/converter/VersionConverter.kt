package kr.lul.support.spring.data.jpa.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import kr.lul.version.Version

@Converter(autoApply = true)
class VersionConverter : AttributeConverter<Version, String> {
    override fun convertToDatabaseColumn(attribute: Version?): String? = attribute?.toString()

    override fun convertToEntityAttribute(dbData: String?): Version? = if (null == dbData) {
        null
    } else {
        Version(dbData)
    }
}
