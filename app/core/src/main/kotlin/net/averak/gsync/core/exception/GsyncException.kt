package net.averak.gsync.core.exception

class GsyncException(val errorCode: ErrorCode, causedBy: Throwable?) : RuntimeException(causedBy) {

    constructor(errorCode: ErrorCode) : this(errorCode, null)

    constructor(causedBy: Throwable?) : this(ErrorCode.UNKNOWN, causedBy)

    override val message: String = causedBy?.toString() ?: errorCode.name
}
