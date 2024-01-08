package net.averak.gsync.infrastructure.json

import net.averak.gsync.testkit.AbstractSpec

class JsonUtils_UT extends AbstractSpec {

    static class Value {

        public String string

        public Integer integer

        Value(String string, Integer integer) {
            this.string = string
            this.integer = integer
        }

        // JSON をデシリアライズする際に引数を持たないコンストラクタが必要になる
        @SuppressWarnings('unused')
        Value() {}

    }

    def "encode: オブジェクトを json 文字列に変換できる"() {
        when:
        final result = JsonUtils.encode(value)

        then:
        // JSON はフィールドの順番を保証しないので、フィールド順が期待値と異なるかもしれない
        result == expectedResult

        where:
        value                   || expectedResult
        new Value("hello", 100) || "{\"string\":\"hello\",\"integer\":100}"
        null                    || "{}"
    }

    def "decode: json 文字列をオブジェクトに変換できる"() {
        when:
        final result = JsonUtils.decode("{\"string\":\"hello\",\"integer\":100}", Value.class)

        then:
        result.string == "hello"
        result.integer == 100
    }
}
