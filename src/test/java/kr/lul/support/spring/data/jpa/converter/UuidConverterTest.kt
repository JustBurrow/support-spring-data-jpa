package kr.lul.support.spring.data.jpa.converter

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import mu.KotlinLogging
import java.util.*

class UuidConverterTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    init {
        given("convertToDatabaseColumn - null 변환하기.") {
            val converter = UuidConverter()
            logger.info("[GIVEN] converter=$converter")

            `when`("null을 변환하면") {
                val dbData = converter.convertToDatabaseColumn(null)
                logger.info("[WHEN] dbData=$dbData")

                then("null로 변환된다.") {
                    dbData.shouldBeNull()
                }
            }
        }

        given("convertToDatabaseColumn - 임의의 UUID 변환하기.") {
            val converter = UuidConverter()
            val uuid = UUID.randomUUID()
            logger.info("[GIVEN] converter=$converter, uuid=$uuid")

            `when`("임의의 UUID를 변환하면") {
                val dbData = converter.convertToDatabaseColumn(uuid)
                logger.info("[WHEN] dbData=$dbData")

                then("파싱 가능한 UUID 문자열을 반환한다.") {
                    dbData.shouldBeInstanceOf<String>()
                    dbData shouldBe uuid.toString()
                    uuid shouldBe UUID.fromString(dbData)
                }
            }
        }

        given("convertToEntityAttribute - null 문자열 변환하기.") {
            val converter = UuidConverter()
            logger.info("[GIVEN] converter=$converter")

            `when`("null을 변환하면") {
                val attribute = converter.convertToEntityAttribute(null)
                logger.info("[WHEN] attribute=$attribute")

                then("null을 반환한다.") {
                    attribute.shouldBeNull()
                }
            }
        }

        given("convertToEntityAttribute - 임의의 UUID 문자열을 파싱하기.") {
            val converter = UuidConverter()
            val uuid = UUID.randomUUID()
            logger.info("[GIVEN] converter=$converter, uuid=$uuid")

            `when`("임의의 UUID 문자열을 변환하면") {
                val attribute = converter.convertToEntityAttribute(uuid.toString())
                logger.info("[WHEN] attribute=$attribute")

                then("원본 UUID와 같은 값을 반환한다.") {
                    attribute.shouldBeInstanceOf<UUID>()
                    attribute shouldNotBeSameInstanceAs uuid
                    attribute shouldBe uuid
                }
            }
        }
    }
}
