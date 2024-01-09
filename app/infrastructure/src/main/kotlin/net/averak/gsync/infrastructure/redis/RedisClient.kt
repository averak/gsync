package net.averak.gsync.infrastructure.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

/**
 * Redis クライアント
 */
@Component
@SuppressWarnings("kotlin:S6518")
class RedisClient(
    private val client: StringRedisTemplate,
) {

    /**
     * キーの値を取得する
     *
     * @return キーが存在しない、もしくは pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/get">Redis Documentation: GET</a>
     */
    fun get(key: String): String? {
        try {
            return client.opsForValue().get(key)
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの存在を確認する
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/exists">Redis Documentation: EXISTS</a>
     */
    fun exists(key: String): Boolean? {
        try {
            return client.execute {
                it.commands().exists(key.toByteArray())
            }
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの有効期限を取得する
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/ttl">Redis Documentation: TTL</a>
     */
    fun ttl(key: String): Long? {
        try {
            return client.execute {
                it.commands().ttl(key.toByteArray())
            }
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの値をセットする (UPSERT)
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/set">Redis Documentation: SET</a>
     */
    fun set(key: String, value: String): Boolean? {
        try {
            return client.execute {
                it.commands().set(key.toByteArray(), value.toByteArray())
            }
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーが存在しない場合に、キーの値をセットする (INSERT)
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/set">Redis Documentation: SET</a>
     */
    fun setnx(key: String, value: String): Boolean? {
        try {
            return client.opsForValue().setIfAbsent(key, value)
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの値をセットする (UPSERT) と同時に、キーの有効期限を設定する
     *
     * @see <a href="https://redis.io/commands/setex">Redis Documentation: SETEX</a>
     */
    fun setex(key: String, value: String, seconds: Long) {
        try {
            client.opsForValue().set(key, value, Duration.ofSeconds(seconds))
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの値を削除する
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/del">Redis Documentation: DEL</a>
     */
    fun del(keys: List<String>): Long? {
        if (keys.isEmpty()) {
            return 0
        }

        try {
            return client.execute {
                it.commands().del(*keys.map { key -> key.toByteArray() }.toTypedArray())
            }
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの有効期限を設定する
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/expire">Redis Documentation: EXPIRE</a>
     */
    fun expire(key: String, seconds: Long): Boolean? {
        try {
            return client.expire(key, Duration.ofSeconds(seconds))
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの値をインクリメントする
     * キーが存在しない場合、0で初期化してからインクリメントされる (1になる)
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    fun incr(key: String): Long? {
        try {
            return client.opsForValue().increment(key)
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * キーの値をデクリメントする
     * キーが存在しない場合、0で初期化してからデクリメントされる (-1になる)
     *
     * @return pipeline / transaction 内で実行された場合は NULL になる
     * @see <a href="https://redis.io/commands/decr">Redis Documentation: DECR</a>
     */
    fun decr(key: String): Long? {
        try {
            return client.opsForValue().decrement(key)
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * データベース内の全てのキーを削除する
     *
     * @see <a href="https://redis.io/commands/flushdb">Redis Documentation: FLUSHDB</a>
     */
    fun flushdb() {
        try {
            client.execute { connection ->
                connection.serverCommands().flushDb()
            }
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }

    /**
     * トランザクション内で処理を実行する
     *
     * @return アトミックなトランザクション内の各コマンドに対応する応答
     * @see <a href="https://redis.io/docs/interact/transactions">Redis Documentation: Transactions</a>
     */
    fun transaction(actions: () -> Unit): List<Any> {
        try {
            client.multi()
            client.setEnableTransactionSupport(true)
            actions()
            return client.exec()
        } catch (e: Exception) {
            throw RedisException(e)
        }
    }
}

class RedisException(causedBy: Throwable) : Exception(causedBy)
