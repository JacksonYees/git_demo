package com.cskt.constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 统一响应对象
 */
@Data
@ApiModel(value = "统一响应对象")
public
class ReturnResult implements Serializable {
    //验证当序列化的时候的serialVersionUID与反序列化的时候的serialVersionUID 不一致的时候，会跑出InvalidCalssException。
    private static final long serialVersionUID = 4312739102572646670L;
    @ApiModelProperty(value = "响应是否成功：成功:true，否:false")
    private String success;
    @ApiModelProperty(value = "异常码")
    private String errorCode;
    @ApiModelProperty(value = "返回响应的提示")
    private String msg;
    @ApiModelProperty(value = "返回响应数据")
    private Object data;

    private ReturnResult(String success, String errorCode, String msg, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    private ReturnResult() {

    }

    /**
     * 正常响应
     */
    public static ReturnResult ok() {
        return ok(ErrorCodeEnum.OK);
    }

    /**
     * 正常响应
     * @param errorCodeEnum 异常码
     */
    public static ReturnResult ok(ErrorCodeEnum errorCodeEnum) {
        return ok(errorCodeEnum,null);
    }
    /**
     *  正常响应带数据
     *  @param data 数据
     * */
    public static ReturnResult ok(Object data) {
        return ok(ErrorCodeEnum.OK, data);
    }
    /***
     * 正常响应
     * @param errorCodeEnum 错误码枚举
     * @param data 数据
     * */
    public static ReturnResult ok(ErrorCodeEnum errorCodeEnum, Object data) {
        return new ReturnResult(String.valueOf(true), errorCodeEnum.getErrorCode(), errorCodeEnum.getMsg(), data);
    }
    /**
     * 异常响应
     */
    public static ReturnResult error() {
        return error(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
    }
    /**
     * 异常响应
     * @param errorCodeEnum 错误码枚举
     * */
    public static ReturnResult error(ErrorCodeEnum errorCodeEnum) {
        return error(errorCodeEnum.getErrorCode(), errorCodeEnum.getMsg());
    }
    /**
     * 异常响应
     * @param errorCode 内部错误码
     * @param msg 错误信息
     * */
    public static ReturnResult error(String errorCode, String msg) {
        return error(false, errorCode, msg);
    }
    /**
     * 异常响应
     * @param success 是否成功标示
     * @param errorCode 内部错误码
     * @param msg 错误信息
     * */
    public static ReturnResult error(Boolean success, String errorCode, String msg) {
        return error(success, errorCode, msg, null); }
    /**
     * 异常响应
     * @param success 是否成功 成功true;否false,一般情况下为true
     * @param errorCode 内部错误码
     * @param msg 错误信息
     * @param data 数据
     * */
    public static ReturnResult error(Boolean success, String errorCode, String msg, Object data) {
        return new ReturnResult(String.valueOf(success), errorCode, msg, data);
    }

}
