package net.averak.gsync.domain.model

data class ClientVersion(
    val iosRequiredClientVersion: RequiredClientVersionSetting,
    val androidRequiredClientVersion: RequiredClientVersionSetting,
) {

    fun greaterThan(os: Os, version: Semver): Boolean {
        return when (os) {
            Os.IOS -> iosRequiredClientVersion.version.greaterThan(version)
            Os.ANDROID -> androidRequiredClientVersion.version.greaterThan(version)
        }
    }

    fun getDownloadUrl(os: Os): String {
        return when (os) {
            Os.IOS -> iosRequiredClientVersion.downloadUrl
            Os.ANDROID -> androidRequiredClientVersion.downloadUrl
        }
    }
}

data class RequiredClientVersionSetting(
    val version: Semver,
    val downloadUrl: String,
)
