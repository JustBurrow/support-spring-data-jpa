package kr.lul.support.spring.data.jpa.db

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import kr.lul.support.spring.data.jpa.sample.TestConfig
import kr.lul.support.spring.data.jpa.sample.TestEntity
import kr.lul.support.spring.data.jpa.sample.TestRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit.MILLIS
import java.util.*

@SpringBootTest(classes = [TestConfig::class])
@Suppress("LeakingThis")
@Transactional
class MySqlTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    @Autowired
    lateinit var repository: TestRepository

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        given("save - 엔티티 저장하기") {
            val uuid = UUID.randomUUID()
            val instant = Instant.now().truncatedTo(MILLIS)
            val zdt = ZonedDateTime.now()
            logger.info("[GIVEN] uuid=$uuid, instant=$instant, zdt=$zdt")

            val entity = TestEntity(uuid, instant, zdt)
            logger.info("[GIVEN] entity=$entity")

            `when`("엔티티를 저장하면") {
                val saved = repository.saveAndFlush(entity)
                logger.info("[WHEN] saved=$saved")

                then("잘 저장된다.") {
                    saved.id shouldBeGreaterThan 0L
                    saved.uuid shouldBe uuid
                    saved.instant shouldBe instant
                    saved.zonedDateTime shouldBe zdt

                    repository.flush()
                    val read = repository.findById(saved.id).get()

                    read shouldBe saved
                    read shouldNotBeSameInstanceAs saved
                    read.uuid shouldBe uuid
                    read.instant shouldBe instant
                    read.zonedDateTime shouldBe zdt
                }
            }
        }
    }
}
