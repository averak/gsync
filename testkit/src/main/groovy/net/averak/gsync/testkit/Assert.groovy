package net.averak.gsync.testkit

import net.averak.gsync.core.exception.GsyncException

import java.sql.Timestamp
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

    static void timestampIs(final LocalDateTime actual, final LocalDateTime expected, final Duration approxDuration = Duration.ofMillis(1000)) {
        assert ChronoUnit.MILLIS.between(actual, expected) <= approxDuration.toMillis()
    }

    static void timestampIs(final Object actual, final LocalDateTime expected, final Duration approxDuration = Duration.ofMillis(1000)) {
        assert actual instanceof Timestamp
        assert ChronoUnit.MILLIS.between(actual.toLocalDateTime(), expected) <= approxDuration.toMillis()
    }
}
