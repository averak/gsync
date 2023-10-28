package net.averak.gsync.infrastructure.json

import net.averak.gsync.AbstractSpec

class JsonUtils_UT extends AbstractSpec {

    def "encode: JSON 文字列にエンコードする"() {
        when:
        final result = JsonUtils.encode(value)

        then:
        result == expectedResult

        where:
        value                         || expectedResult
        new SampleValue(100, "hello") || "{\"intValue\":100,\"stringValue\":\"hello\"}"
        null                          || "{}"
    }

    def "decode: JSON 文字列をデコードする"() {
        given:
        final json = "{\"intValue\":100,\"stringValue\":\"hello\"}"

        when:
        final result = JsonUtils.decode(json, SampleValue.class)

        then:
        result.intValue == 100
        result.stringValue == "hello"
    }

    static class SampleValue {

        public Integer intValue

        public String stringValue

        SampleValue() {}

        SampleValue(Integer intValue, String stringValue) {
            this.intValue = intValue
            this.stringValue = stringValue
        }

    }

}
