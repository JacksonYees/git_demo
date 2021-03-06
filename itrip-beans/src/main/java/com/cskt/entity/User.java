package com.cskt.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
    * 用户表
    */
@ApiModel(value="com-cskt-entity-User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "itrip_user")
public class User  implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键,用户id")
    private Long id;

    /**
     * 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value="若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号")
    private String userCode;

    /**
     * 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     */
    @TableField(value = "user_password")
    @ApiModelProperty(value="若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码")
    private String userPassword;

    /**
     * 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     */
    @TableField(value = "user_type")
    @ApiModelProperty(value="用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）")
    private Integer userType;

    /**
     * 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     */
    @TableField(value = "flat_id")
    @ApiModelProperty(value="平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）")
    private Long flatId;

    /**
     * 用户昵称
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value="用户昵称")
    private String userName;

    /**
     * 微信号
     */
    @TableField(value = "we_chat")
    @ApiModelProperty(value="微信号")
    private String weChat;

    /**
     * qq账号
     */
    @TableField(value = "qq")
    @ApiModelProperty(value="qq账号")
    private String qq;

    /**
     * 微博账号
     */
    @TableField(value = "weibo")
    @ApiModelProperty(value="微博账号")
    private String weibo;

    /**
     * 百度账号
     */
    @TableField(value = "baidu")
    @ApiModelProperty(value="百度账号")
    private String baidu;

    @TableField(value = "creation_date",fill = FieldFill.INSERT)
    @ApiModelProperty(value="")
    private Date creationDate;

    @TableField(value = "created_by")
    @ApiModelProperty(value="")
    private Long createdBy;

    @TableField(value = "modify_date",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="")
    private Date modifyDate;

    @TableField(value = "modified_by")
    @ApiModelProperty(value="")
    private Long modifiedBy;

    /**
     * 是否激活,(0 false，1 true,默认是0)
     */
    @TableField(value = "activated")
    @ApiModelProperty(value="是否激活,(0 false，1 true,默认是0)")
    private Integer activated;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_PASSWORD = "user_password";

    public static final String COL_USER_TYPE = "user_type";

    public static final String COL_FLAT_ID = "flat_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_WE_CHAT = "we_chat";

    public static final String COL_QQ = "qq";

    public static final String COL_WEIBO = "weibo";

    public static final String COL_BAIDU = "baidu";

    public static final String COL_CREATION_DATE = "creation_date";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_MODIFY_DATE = "modify_date";

    public static final String COL_MODIFIED_BY = "modified_by";

    public static final String COL_ACTIVATED = "activated";

    public static final String COL_IS_DELETED = "is_deleted";
}