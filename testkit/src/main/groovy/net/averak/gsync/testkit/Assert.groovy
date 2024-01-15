package net.averak.gsync.testkit

import net.averak.gsync.core.exception.GsyncException

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
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

    @SuppressWarnings('UnnecessaryQualifiedReference')
    static void timestampIs(final com.google.protobuf.Timestamp actual, final LocalDateTime expected, final Duration approxDuration = Duration.ofMillis(1000)) {
        assert ChronoUnit.MILLIS.between(LocalDateTime.ofInstant(Instant.ofEpochSecond(actual.seconds, actual.nanos), ZoneId.systemDefault()), expected) <= approxDuration.toMillis()
    }

    @SuppressWarnings('UnnecessaryQualifiedReference')
    static void timestampIs(final Object actual, final LocalDateTime expected, final Duration approxDuration = Duration.ofMillis(1000)) {
        assert actual instanceof java.sql.Timestamp
        assert ChronoUnit.MILLIS.between(actual.toLocalDateTime(), expected) <= approxDuration.toMillis()
    }
}
