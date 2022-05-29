package ru.net.arh.study.endpoint

import io.grpc.StatusRuntimeException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.net.arh.study.data.BaseResponse
import ru.net.arh.study.data.ErrorData

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun onException(e: Exception): ResponseEntity<BaseResponse> {
        return BaseResponse(
            errorData = ErrorData(
                success = false,
                message = e.message
            )
        ).let { ResponseEntity.ok().body(it) }
    }


    @ExceptionHandler
    fun onStatusException(e: StatusRuntimeException): ResponseEntity<BaseResponse> {
        return BaseResponse(
            errorData = ErrorData(
                success = false,
                errorCode = e.status.code.name,
                message = e.status.description
            )
        ).let { ResponseEntity.ok().body(it) }
    }
}