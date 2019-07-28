package com.seongenie.handycoin.infra

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handle(ex: Exception, request: HttpServletRequest, response: HttpServletResponse) : ResponseEntity<Any> {
        return if(ex is NullPointerException) ResponseEntity(HttpStatus.BAD_REQUEST) else ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
}