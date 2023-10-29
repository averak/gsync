package net.averak.gsync.testkit

import org.apache.commons.lang3.RandomStringUtils
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import java.time.LocalDate
import java.util.*

class Faker {

    companion object {

        private lateinit var easyRandom: EasyRandom

        /**
         * 初期化する
         * テストのエントリーポイントから必ず呼び出すこと
         */
        @JvmStatic
        fun init(randomizers: List<IRandomizer<Any>>) {
            val easyRandomParameters = EasyRandomParameters()
            randomizers.forEach {
                easyRandomParameters.randomize(it.getTypeToGenerate(), it)
            }
            easyRandom = EasyRandom(easyRandomParameters)
        }

        /**
         * 各フィールドにランダム値を格納したフェイクオブジェクトを生成する
         *
         * @param clazz target class
         * @param fields ランダムに生成されたフィールドの値を強制上書きするマップ <field name, value>
         */
        @JvmStatic
        @JvmOverloads
        fun <T> fake(clazz: Class<T>, fields: Map<String, Any> = mapOf()): T {
            val obj = easyRandom.nextObject(clazz)
            fields.forEach { (key, value) ->
                val field = clazz.getDeclaredField(key)
                field.isAccessible = true
                field[obj] = value
                field.isAccessible = false
            }
            return obj
        }

        /**
         * 各フィールドにランダム値を格納したフェイクオブジェクトリストを生成する
         *
         * @param clazz target class
         * @param size number of generated objects
         * @param fields ランダムに生成されたフィールドの値を強制上書きするマップ <field name, value>
         */
        @JvmStatic
        @JvmOverloads
        fun <T> fakes(clazz: Class<T>, size: Int = 10, fields: Map<String, Any> = mapOf()): List<T> {
            val objs = easyRandom.objects(clazz, size).toList()
            fields.forEach { (key, value) ->
                val field = clazz.getDeclaredField(key)
                field.isAccessible = true
                objs.forEach { field[it] = value }
                field.isAccessible = false
            }
            return objs
        }

        /**
         * メールアドレスを生成する
         */
        @JvmStatic
        fun email(): String {
            return "${RandomStringUtils.randomAlphanumeric(10)}@${RandomStringUtils.randomAlphanumeric(5)}.com".lowercase(Locale.getDefault())
        }

        /**
         * パスワードを生成する
         */
        @JvmStatic
        fun password(): String {
            return "b9Fj5QYP" + RandomStringUtils.randomAlphanumeric(8)
        }

        /**
         * URLを生成する
         */
        @JvmStatic
        fun url(): String {
            return "https://${RandomStringUtils.randomAlphanumeric(5)}.com/${RandomStringUtils.randomAlphanumeric(10)}"
        }

        /**
         * 数字のみの文字列を生成する
         */
        @JvmStatic
        @JvmOverloads
        fun numeric(length: Int = 31): String {
            return RandomStringUtils.randomNumeric(length)
        }

        /**
         * 英数字の文字列を生成する
         */
        @JvmStatic
        @JvmOverloads
        fun alphanumeric(length: Int = 31): String {
            return RandomStringUtils.randomAlphanumeric(length)
        }

        /**
         * 整数を生成する
         */
        @JvmStatic
        @JvmOverloads
        fun integer(min: Int = 0, max: Int = Int.MAX_VALUE): Int {
            val rand = Random()
            return min + rand.nextInt(max - min)
        }

        /**
         * 自然数を生成する
         */
        @JvmStatic
        @JvmOverloads
        fun naturalNumber(max: Int = Int.MAX_VALUE): Int {
            return integer(1, max)
        }

        /**
         * BASE64エンコードされた文字列を生成
         */
        @JvmStatic
        fun base64encoded(): String {
            val encoder = Base64.getEncoder()
            return encoder.encodeToString(RandomStringUtils.randomAlphanumeric(10).toByteArray())
        }

        /**
         * リストからランダムに要素を抽出する
         */
        @JvmStatic
        fun <T> dice(elements: List<T>): T {
            return elements[integer(0, elements.size - 1)]
        }

        /**
         * UUIDv4を生成する
         */
        @JvmStatic
        fun uuidv4(): String {
            return UUID.randomUUID().toString()
        }

        /**
         * UUIDv5を生成する
         */
        @JvmStatic
        fun uuidv5(name: String): String {
            return UUID.nameUUIDFromBytes(name.toByteArray()).toString()
        }

        /**
         * 本日の日付を生成する
         */
        @JvmStatic
        fun today(): LocalDate {
            return LocalDate.now()
        }

        /**
         * 本日の日付を生成する
         */
        @JvmStatic
        fun tomorrow(): LocalDate {
            return LocalDate.now().plusDays(1)
        }

        /**
         * 本日の日付を生成する
         */
        @JvmStatic
        fun yesterday(): LocalDate {
            return LocalDate.now().minusDays(1)
        }
    }
}
