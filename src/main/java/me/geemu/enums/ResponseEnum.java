package me.geemu.enums;

import lombok.Getter;

/**
 * @Author: Geemu
 * Email:cfmmail@sina.com
 * Date: 2017/12/5 13:51
 * Description:
 */
public enum ResponseEnum {

    /**
     * 登陆失败
     */
    ACCOUNT_OR_PASSWORD_FAIL(10001, "用户名或密码错误"),

    /**
     * 参数校验失败
     */
    PARAMS_ERROR(10002, "参数校验失败"),

    /**
     * 无效的token
     */
    FORBIDDEN_ERROR(10003, "无效的token"),


    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "内部错误"),;

    @Getter
    private Integer code;

    @Getter
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
