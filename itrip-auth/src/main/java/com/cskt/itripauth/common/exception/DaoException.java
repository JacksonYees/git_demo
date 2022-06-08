package com.cskt.itripauth.common.exception;

import com.cskt.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Dao层的错误信息
 */
@Data
@ApiModel(value = "自定义Dao异常")
public class DaoException extends RuntimeException {

    @ApiModelProperty(value = "Dao异常状态码")
    private String errorCode; public DaoException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public DaoException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }
}
