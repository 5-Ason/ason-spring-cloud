package com.ason.result;

/**
 * 应用系统级别的返回码
 * Created by Ason on 2017/9/13.
 */
public enum ResponseInfoEnum implements ResponseInfo {
    SUCCESS("00000", "success"),
    ERROR("40000", "error"),

    /**API接口返回码*/
    ERROR_41000("41000", "服务异常出错"),

    FAIL_31000("31000", "缺少必要参数");

    private String code;
    private String message;

    ResponseInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
