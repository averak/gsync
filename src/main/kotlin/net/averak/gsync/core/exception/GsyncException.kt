package net.averak.gsync.core.exception

class GsyncException(val errorCode: ErrorCode, val causedBy: Throwable?) : RuntimeException(errorCode.summary, causedBy) {
    constructor(errorCode: ErrorCode) : this(errorCode, null)
}
