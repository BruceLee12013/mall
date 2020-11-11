package com.jayden.mall.exception;

/**
 * 描述：     统一异常
 */
public class BusinessException extends RuntimeException {

    private final Integer code;
    private final String message;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
