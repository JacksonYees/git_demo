package com.cskt.itripauth.common.exception;

import com.cskt.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统自定义异常
 */
@Data
@ApiModel(value = "自定义系统异常")
public class SysException extends RuntimeException{
    @ApiModelProperty(value = "系统常状态码")
    private String errorCode; public SysException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public SysException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }
}
