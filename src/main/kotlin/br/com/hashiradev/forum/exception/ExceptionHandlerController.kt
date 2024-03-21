package br.com.hashiradev.forum.exception

import br.com.hashiradev.forum.DTO.ExceptionView
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice

class ExceptionHandlerController{
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFoundException(
        exception: NotFoundException,
        request: HttpServletRequest,
    ): ExceptionView {
        return ExceptionView(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.name,
            exception.message,
            request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun HandlerValidationException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ExceptionView {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{ errorMessage.put(it.field, it.defaultMessage)
        }
//        var errorMessage = ""
//        exception.bindingResult.fieldErrors.forEach { errorMessage += "${it.field}=>${it.defaultMessage},"  }
        return ExceptionView(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.name,
            errorMessage.toString(),
            request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ExceptionView {
        return ExceptionView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}