package net.averak.gsync.domain.model

import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import java.util.*

/**
 * プレイヤーデータを格納する汎用的な Key-Value 形式のストレージ
 *
 * サーバはストレージの中身について一切感知しないため、クライアントで独自にチート対策を行う必要がある。
 * サーバ開発コストを抑えたい場合、もしくは複数端末で同じプレイヤーデータを共有する必要がある場合に利用すること。
 */
data class PlayerStorage(
    val playerID: UUID,
    val tenantID: UUID,
    var revision: UUID,
    /**
     * [net.averak.gsync.domain.repository.IPlayerStorageRepository.PlayerStorageCriteria] による検索結果が格納されるので、全エントリーが入っているとは限らない
     */
    val entries: MutableList<PlayerStorageEntry>,
) {

    companion object {

        private val FIRST_REVISION = UUID.fromString("00000000-0000-0000-0000-000000000000")

        @JvmStatic
        fun ofFirstRevision(playerID: UUID, tenantID: UUID): PlayerStorage {
            return PlayerStorage(
                playerID = playerID,
                tenantID = tenantID,
                revision = FIRST_REVISION,
                entries = mutableListOf(),
            )
        }
    }

    /**
     * プレイヤーストレージに変更を加える前に、リビジョンが一致するか検証する
     */
    fun validate(revision: UUID) {
        if (this.revision != revision) {
            throw GsyncException(ErrorCode.PLAYER_STORAGE_REVISION_MISMATCH)
        }
    }

    fun set(vararg entries: PlayerStorageEntry) {
        entries.forEach {
            val index = this.entries.indexOfFirst { entry -> entry.key == it.key }
            if (index == -1) {
                this.entries.add(it)
            } else {
                this.entries[index] = it
            }
        }
        revision = UUID.randomUUID()
    }

    fun clearAll() {
        entries.forEach {
            it.clear()
        }
        revision = UUID.randomUUID()
    }
}

data class PlayerStorageEntry(
    val key: String,
    var value: ByteArray,
) {

    init {
        // 最大で 100 KiB まで許容する (Spanner のパフォーマンス都合)
        if (value.size > 100 * 1024) {
            throw GsyncException(ErrorCode.PLAYER_STORAGE_VALUE_EXCESS_MAX_SIZE)
        }
    }

    fun clear() {
        value = byteArrayOf()
    }

    fun isCleared(): Boolean {
        return value.isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerStorageEntry

        if (key != other.key) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + value.contentHashCode()
        return result
    }
}
