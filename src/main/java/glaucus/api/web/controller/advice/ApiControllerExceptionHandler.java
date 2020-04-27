package glaucus.api.web.controller.advice;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

import glaucus.api.service.exceptions.PersonCustomException;
import glaucus.api.service.exceptions.PersonValidationException;
import glaucus.api.web.utils.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ApiControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception exception) {
        log.error("Exception occurred. {}\n{}", exception.getMessage(), exception);
        return ResponseEntityUtils.errorResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                MethodArgumentNotValidException exception) {
        log.error("Exception occurred. {}\n{}", exception.getMessage(), exception);
        String resultMessage = String.join(" | ",
                exception.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
        return ResponseEntityUtils.errorResponseEntity(resultMessage);
    }

    @ExceptionHandler({PersonValidationException.class})
    public ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                PersonValidationException exception) {
        log.error("Exception occurred. {}\n{}", exception.getMessage(), exception);
        String resultMessage = String.join(" | ",
                exception.getErrors().getAllErrors().stream().map(oe -> oe.getDefaultMessage()).collect(Collectors.toList()));
        return ResponseEntityUtils.errorResponseEntity(resultMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(HttpServletRequest request,
                                                             ConstraintViolationException exception) {
        log.error("Exception occurred. {}\n{}", exception.getMessage(), exception);
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        int index = 0;
        int len = constraintViolations.size();
        for (ConstraintViolation cv : constraintViolations) {
            if (index == len - 1) {
                sb.append(cv.getMessage());
                break;
            }
            sb.append(cv.getMessage()).append(StringUtils.LF);
            index++;
        }
        String resultMessage = sb.toString();
        return ResponseEntityUtils.errorResponseEntity(resultMessage);
    }

    @ExceptionHandler(ConstraintDefinitionException.class)
    public ResponseEntity handleConstraintDefinitionException(HttpServletRequest request,
                                                              ConstraintDefinitionException exception) {
        log.error("Exception occurred. {}\n{}", exception.getMessage(), exception);
        return ResponseEntityUtils.errorResponseEntity(exception.getMessage());
    }

    @ExceptionHandler(PersonCustomException.class)
    public ResponseEntity handlePersonCustomException(HttpServletRequest request, PersonCustomException exception) {
        log.info("PersonCustomException occurred. {}\n{}", exception.getMessage(), exception);
        return ResponseEntityUtils.errorResponseEntity(exception.getMessage());
    }

}
