package kr.lul.support.spring.data.jpa.mapping

import kr.lul.support.spring.data.jpa.entity.EmbeddableVersion
import kr.lul.version.Version

/**
 * [EmbeddableVersion] 기본 매핑 정보.
 *
 * @see EmbeddableVersion
 * @see Version
 */
@Suppress("MemberVisibilityCanBePrivate")
object EmbeddableVersionMapping {
    const val PROP_MAJOR = "major"
    const val PROP_MINOR = "minor"
    const val PROP_PATCH = "patch"
    const val PROP_PRE_RELEASE = "preRelease"
    const val PROP_BUILD = "build"

    const val COL_MAJOR = "`$PROP_MAJOR`"
    const val COL_MINOR = "`$PROP_MINOR`"
    const val COL_PATCH = "`$PROP_PATCH`"
    const val COL_PRE_RELEASE = "`pre_release`"
    const val COL_BUILD = "`$PROP_BUILD`"
}
