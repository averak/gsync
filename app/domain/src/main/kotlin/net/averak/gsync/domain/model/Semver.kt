package net.averak.gsync.domain.model

data class Semver(
    val major: Int,
    val minor: Int,
    val patch: Int,
) {

    companion object {

        private val regex = Regex("""^(\d+)\.(\d+)\.(\d+)$""")

        @JvmStatic
        fun parse(version: String): Semver {
            val match = regex.find(version) ?: throw IllegalArgumentException("Invalid version format: $version")
            val (major, minor, patch) = match.destructured
            return Semver(major.toInt(), minor.toInt(), patch.toInt())
        }
    }

    fun lessThan(other: Semver): Boolean {
        return when {
            major < other.major -> true
            major > other.major -> false
            minor < other.minor -> true
            minor > other.minor -> false
            patch < other.patch -> true
            patch > other.patch -> false
            else -> false
        }
    }

    fun greaterThan(other: Semver): Boolean {
        return when {
            major > other.major -> true
            major < other.major -> false
            minor > other.minor -> true
            minor < other.minor -> false
            patch > other.patch -> true
            patch < other.patch -> false
            else -> false
        }
    }

    override fun toString(): String {
        return "$major.$minor.$patch"
    }
}
