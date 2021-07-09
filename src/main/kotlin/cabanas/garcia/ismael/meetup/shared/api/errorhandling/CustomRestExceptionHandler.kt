package cabanas.garcia.ismael.meetup.shared.api.errorhandling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CustomRestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onMethodArgumentNotValidException(e: MethodArgumentNotValidException): ApiError {
        val fieldErrors: MutableList<FieldError> = mutableListOf()
        for (fieldError in e.bindingResult.fieldErrors) {
            fieldErrors.add(FieldError(fieldError.field, fieldError.defaultMessage))
        }
        return ApiError("Bad request data.", fieldErrors)
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onMissingRequestHeaderExceptionException(e: MissingRequestHeaderException): ApiError {
        return ApiError("X-Meeting-User-Info header must be set.")
    }
}

data class FieldError(val field: String, val error: String?)

data class ApiError(val message: String, val errors: List<FieldError> = emptyList())