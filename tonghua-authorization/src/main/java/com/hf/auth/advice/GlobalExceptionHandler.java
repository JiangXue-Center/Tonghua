package com.hf.auth.advice;

import com.hf.core.exception.AuthException;
import com.hf.core.exception.TokenGenerateException;
import com.hf.core.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Result> authExceptionHandler(HttpServletRequest req, AuthException e) {
        logger.error("登录失败", e);
        return new ResponseEntity<>(Result.fail("登录失败"), HttpStatus.BAD_REQUEST);
    }

}
