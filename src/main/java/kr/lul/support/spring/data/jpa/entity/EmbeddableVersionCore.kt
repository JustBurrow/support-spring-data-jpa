package kr.lul.support.spring.data.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Transient
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionCoreMapping.COL_MAJOR
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionCoreMapping.COL_MINOR
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionCoreMapping.COL_PATCH
import kr.lul.version.Core
import java.util.*

/**
 * 각 버전 속성 정보에 따라 인덱스를 지정할 수 있도록 컬럼 분할한 엔티티.
 * 인덱스가 필요덦을 경우엔 [kr.lul.support.spring.data.jpa.converter.VersionConverter]를 사용해 단일 문자열을 사용한다.
 */
@Embeddable
class EmbeddableVersionCore(
    @field:Column(name = COL_MAJOR, nullable = false)
    val major: Int,
    @field:Column(name = COL_MINOR, nullable = false)
    val minor: Int,
    @field:Column(name = COL_PATCH, nullable = false)
    val patch: Int
) : Comparable<EmbeddableVersionCore> {
    @field:Transient
    val version = Core(major, minor, patch)

    constructor(core: Core) : this(
        core.major,
        core.minor,
        core.patch
    )

    override fun compareTo(other: EmbeddableVersionCore): Int = version.compareTo(other.version)

    override fun equals(other: Any?) = this === other || (
            other is EmbeddableVersionCore
                    && major == other.major
                    && minor == other.minor
                    && patch == other.patch
            )

    override fun hashCode() = Objects.hash(major, minor, patch)

    override fun toString() = "$major.$minor.$patch"
}
