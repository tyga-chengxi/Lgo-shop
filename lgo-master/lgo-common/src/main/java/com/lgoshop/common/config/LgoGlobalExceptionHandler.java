package com.lgoshop.common.config;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.common.exception.LgoBusinessException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class LgoGlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(LgoGlobalExceptionHandler.class);

    @ExceptionHandler(LgoBusinessException.class)
    public LgoResult<Void> handleBusinessException(LgoBusinessException e) {
        log.warn("【{}】{}", e.getCode(), e.getMessage());
        return LgoResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public LgoResult<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .findFirst().orElse("参数校验失败");
        log.warn("【400】{}", msg);
        return LgoResult.warn(msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public LgoResult<Void> handleConstraintViolation(ConstraintViolationException e) {
        log.warn("【400】{}", e.getMessage());
        return LgoResult.warn(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public LgoResult<Void> handleBindException(BindException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .findFirst().orElse("参数绑定失败");
        log.warn("【400】{}", msg);
        return LgoResult.warn(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public LgoResult<Void> handleMissingParam(MissingServletRequestParameterException e) {
        log.warn("【400】缺少参数: {}", e.getParameterName());
        return LgoResult.warn("缺少必要参数: " + e.getParameterName());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public LgoResult<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("【400】请求体格式错误");
        return LgoResult.warn("请求体格式错误");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public LgoResult<Void> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.warn("【400】参数 {} 类型不匹配", e.getName());
        return LgoResult.warn("参数" + e.getName() + "类型不匹配");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public LgoResult<Void> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        log.warn("【415】不支持的 Content-Type");
        return LgoResult.error(415, "不支持的 Content-Type: " + e.getContentType());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public LgoResult<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("【405】不支持 {} 请求", e.getMethod());
        return LgoResult.error(405, "不支持" + e.getMethod() + "请求");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public LgoResult<Void> handleNoHandlerFound(NoHandlerFoundException e) {
        log.warn("【404】接口不存在: {}", e.getRequestURL());
        return LgoResult.error(404, "接口不存在");
    }

    @ExceptionHandler(AuthenticationException.class)
    public LgoResult<Void> handleAuthenticationException(AuthenticationException e) {
        log.warn("【401】暂未登录或token已经过期");
        return LgoResult.error(401, "暂未登录或token已经过期");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public LgoResult<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("【403】没有相关权限");
        return LgoResult.error(403, "没有相关权限");
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public LgoResult<Void> handleBadSqlGrammarException(BadSqlGrammarException e) {
        log.error("【500】SQL异常", e);
        return LgoResult.error("数据库执行异常");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public LgoResult<Void> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.error("【500】数据操作异常", e);
        return LgoResult.error("数据操作异常");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public LgoResult<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("【500】数据重复", e);
        return LgoResult.error("数据重复，请检查");
    }

    @ExceptionHandler(Exception.class)
    public LgoResult<Void> handleException(Exception e) {
        log.error("【500】系统异常", e);
        return LgoResult.error("系统繁忙，请稍后重试");
    }
}
