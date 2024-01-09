package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import org.springframework.http.HttpStatus

class GlobalRestControllerAdvice_IT extends AbstractController_IT {

    def "異常系 存在しないパスの場合はエラーを返す"() {
        given:
        final path = "/api/xxx"

        expect:
        final request = this.getRequest(path)
        execute(request, HttpStatus.NOT_FOUND, new GsyncException(ErrorCode.NOT_FOUND_API))
    }
}
