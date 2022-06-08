package com.cskt.itripauth.common.exception;
import com.cskt.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 自定义异常
 */
@Data
@ApiModel(value = "自定义service层异常")
public class ServiceException extends RuntimeException{

    @ApiModelProperty(value = "service常状态码")
    private String errorCode; public ServiceException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public ServiceException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }
}
