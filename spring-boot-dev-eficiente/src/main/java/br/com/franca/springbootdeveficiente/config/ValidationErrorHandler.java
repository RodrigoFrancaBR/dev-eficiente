package br.com.franca.springbootdeveficiente.config;

import br.com.franca.springbootdeveficiente.dto.out.ValidationErrorsOutputDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationErrorHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {
        log.info("MethodArgumentNotValidException: {}", exception);
        return getValidationErrorsOutputDto(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    public ValidationErrorsOutputDto handleValidationErrorFormUrl(BindException exception) {
        log.info("BindException: {}", exception);
        return getValidationErrorsOutputDto(exception);
    }

    /**
     * avaliar se vale apena fazer isso.
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidFormatException.class})
    public ValidationErrorsOutputDto handlerInvalidFormatException(InvalidFormatException exception) {
        log.info("InvalidFormatException: {}", exception);
        ValidationErrorsOutputDto validationErrorsOutputDto = new ValidationErrorsOutputDto();
        validationErrorsOutputDto.addError("Data inválida: " + exception.getValue().toString());
        return validationErrorsOutputDto;
    }

    private ValidationErrorsOutputDto getValidationErrorsOutputDto(BindException bindException) {
        List<ObjectError> globalErrors = bindException.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
        ValidationErrorsOutputDto validationErrorsOutputDto = new ValidationErrorsOutputDto();
        globalErrors.forEach(error -> validationErrorsOutputDto.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrorsOutputDto.addFieldError(error.getField(), errorMessage);
        });
        return validationErrorsOutputDto;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
