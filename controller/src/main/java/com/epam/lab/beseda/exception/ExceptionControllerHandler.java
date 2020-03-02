package com.epam.lab.beseda.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static com.epam.lab.beseda.util.LoggerName.ERROR_LOGGER;

@ControllerAdvice
public class ExceptionControllerHandler {

    private static Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @ExceptionHandler(value = RuntimeException.class)
    public String runtimeErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        logger.error(e);
        return e.getMessage();
    }

    @ExceptionHandler(value = ServiceLayerException.class)
    public String  defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error(e);
        return e.getMessage();
    }

}
