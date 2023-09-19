package com.hf.userplatform;

import com.hf.core.exception.EmailFormatException;
import com.hf.core.exception.TokenGenerateException;
import com.hf.core.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.hf.core.enums.ExceptionEnums.EMAIL_FORMAT_ERROR;
import static com.hf.core.enums.ExceptionEnums.TOKEN_GENARATE_ERROR;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ResponseEntity<Result> runtimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        logger.error("运行时异常: ", e);
        return new ResponseEntity<>(Result.fail(""), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenGenerateException.class)
    public ResponseEntity<Result> tokenGenerateExceptionHandler(HttpServletRequest req, TokenGenerateException e) {
        logger.error("token生成失败: ", e);
        return new ResponseEntity<>(Result.fail(TOKEN_GENARATE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<Result> emailFormatExceptionHandler(HttpServletRequest req, EmailFormatException e) {
        logger.error("email格式错误:", e);
        return new ResponseEntity<>(Result.fail(EMAIL_FORMAT_ERROR), HttpStatus.BAD_REQUEST);
    }

}
