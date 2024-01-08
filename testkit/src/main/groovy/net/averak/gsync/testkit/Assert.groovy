package net.averak.gsync.testkit

import net.averak.gsync.core.exception.GsyncException

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Assert {

    static void exceptionIs(final GsyncException actual, final GsyncException expected) {
        assert expected.class == actual.class
        assert expected.errorCode == actual.errorCode
    }

    static void exceptionIs(final Exception actual, final Exception expected) {
        assert expected.class == actual.class
        assert expected.message == actual.message
    }

    /**
     * LocalDateTime が一致するか判定
     *
     * @param approxDuration 許容する誤差
     */
    static void localDateTimeIs(final LocalDateTime actual, final LocalDateTime expected, final Duration approxDuration = Duration.ofSeconds(5)) {
        assert ChronoUnit.MILLIS.between(actual as LocalDateTime, expected) <= approxDuration.toMillis()
    }

}
