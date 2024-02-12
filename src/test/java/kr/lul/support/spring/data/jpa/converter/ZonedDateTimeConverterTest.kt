package kr.lul.support.spring.data.jpa.converter

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import mu.KotlinLogging
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME

class ZonedDateTimeConverterTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    lateinit var converter: ZonedDateTimeConverter

    init {
        beforeTest {
            when {
                true == it.name.prefix?.startsWith("Given:") -> {
                    converter = ZonedDateTimeConverter()
                }
            }
        }

        given("convertToDatabaseColumn - null은 null로 변환하기.") {
            `when`("null을 변환하면") {
                val dbData = converter.convertToDatabaseColumn(null)
                logger.info("[WHEN] dbData=$dbData")

                then("null을 반환한다.") {
                    dbData.shouldBeNull()
                }
            }
        }

        given("convertToDatabaseColumn - 현재 시각 변환하기.") {
            val attribute = ZonedDateTime.now()
            val formatter = ISO_ZONED_DATE_TIME
            logger.info("[GIVEN] attribute=$attribute, formatter=$formatter")

            `when`("String으로 변환하면") {
                val dbData = converter.convertToDatabaseColumn(attribute)
                logger.info("[WHEN] dbData=$dbData")

                then("ISO 문자열로 변환된다.") {
                    dbData shouldBe formatter.format(attribute)
                    ZonedDateTime.parse(dbData, formatter) shouldBe attribute
                }
            }
        }

        given("convertToEntityAttribute - null은 null로 변환한다") {
            `when`("null을 변환하면") {
                val attribute = converter.convertToEntityAttribute(null)
                logger.info("[WHEN] attribute=$attribute")

                then("null이 나온다.") {
                    attribute.shouldBeNull()
                }
            }
        }

        given("convertToEntityAttribute - 현재 시각정보를 변환하기") {
            val now = ZonedDateTime.now()
            val dbData = now.format(ISO_ZONED_DATE_TIME)
            logger.info("[GIVEN] now=$now, dbData=$dbData")

            `when`("현재 시각의 ISO 문자열을 변환하면") {
                val attribute = converter.convertToEntityAttribute(dbData)
                logger.info("[WHEN] attribute=$attribute")

                then("현재 시각이 나온다.") {
                    attribute shouldBe now
                }
            }
        }

        given("상호 변환 테스트") {
            val now = ZonedDateTime.now()
            logger.info("[GIVEN] now=$now")

            `when`("상호 변환하면") {
                val restored = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now))
                logger.info("[WHEN] restored=$restored")

                then("원본이 복원된다.") {
                    restored shouldBe now
                    restored shouldNotBeSameInstanceAs now
                }
            }
        }
    }
}
