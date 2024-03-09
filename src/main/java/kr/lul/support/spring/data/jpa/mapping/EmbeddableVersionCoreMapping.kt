package kr.lul.support.spring.data.jpa.mapping

import kr.lul.support.spring.data.jpa.entity.EmbeddableVersionCore
import kr.lul.version.Version

/**
 * [EmbeddableVersionCore] 기본 매핑 정보.
 *
 * @see EmbeddableVersionCore
 * @see Version
 */
object EmbeddableVersionCoreMapping {
    const val PROP_MAJOR = "major"
    const val PROP_MINOR = "minor"
    const val PROP_PATCH = "patch"

    const val COL_MAJOR = "`$PROP_MAJOR`"
    const val COL_MINOR = "`$PROP_MINOR`"
    const val COL_PATCH = "`$PROP_PATCH`"
}