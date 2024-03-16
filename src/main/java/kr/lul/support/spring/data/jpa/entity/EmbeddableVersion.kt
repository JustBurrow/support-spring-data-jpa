package kr.lul.support.spring.data.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Transient
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionMapping.COL_BUILD
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionMapping.COL_MAJOR
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionMapping.COL_MINOR
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionMapping.COL_PATCH
import kr.lul.support.spring.data.jpa.mapping.EmbeddableVersionMapping.COL_PRE_RELEASE
import kr.lul.version.Build
import kr.lul.version.Core
import kr.lul.version.PreRelease
import kr.lul.version.Version
import java.util.*

/**
 * 각 버전 속성 정보에 따라 인덱스를 지정할 수 있도록 컬럼 분할한 엔티티.
 * 인덱스가 필요덦을 경우엔 [kr.lul.support.spring.data.jpa.converter.VersionConverter]를 사용해 단일 문자열을 사용한다.
 */
@Embeddable
class EmbeddableVersion(
    @field:Column(name = COL_MAJOR, nullable = false)
    val major: Int,
    @field:Column(name = COL_MINOR, nullable = false)
    val minor: Int,
    @field:Column(name = COL_PATCH, nullable = false)
    val patch: Int,
    @field:Column(name = COL_PRE_RELEASE)
    val preRelease: String? = null,
    @field:Column(name = COL_BUILD)
    val build: String? = null
) : Comparable<EmbeddableVersion> {
    @field:Transient
    val version = Version(
        Core(major, minor, patch),
        if (null == preRelease) {
            null
        } else {
            PreRelease(preRelease)
        },
        if (null == build) {
            null
        } else {
            Build(build)
        }
    )

    constructor(version: Version) : this(
        version.core.major,
        version.core.minor,
        version.core.patch,
        version.preRelease?.toString(),
        version.build?.toString()
    )

    override fun compareTo(other: EmbeddableVersion): Int = version.compareTo(other.version)

    override fun equals(other: Any?) = this === other || (
            other is EmbeddableVersion
                    && major == other.major
                    && minor == other.minor
                    && patch == other.patch
                    && preRelease == other.preRelease
                    && build == other.build
            )

    override fun hashCode() = Objects.hash(major, minor, patch, preRelease, build)

    override fun toString() = "$major.$minor.$patch${
        if (null != preRelease) "-$preRelease" else ""
    }${
        if (null != build) "+$build" else ""
    }"
}
