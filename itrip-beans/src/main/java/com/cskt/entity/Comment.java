package com.cskt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
    * 评论表
    */
@ApiModel(value="com-cskt-entity-Comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "itrip_comment")
public class Comment implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 如果产品是酒店的话 改字段保存酒店id
     */
    @TableField(value = "hotel_id")
    @ApiModelProperty(value="如果产品是酒店的话 改字段保存酒店id")
    private Long hotelId;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    @ApiModelProperty(value="商品id")
    private Long productId;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value="订单id")
    private Long orderId;

    /**
     * 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     */
    @TableField(value = "product_type")
    @ApiModelProperty(value="商品类型(0:旅游产品 1:酒店产品 2:机票产品)")
    private Integer productType;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="评论内容")
    private String content;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户编号")
    private Long userId;

    /**
     * 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     */
    @TableField(value = "is_having_img")
    @ApiModelProperty(value="是否包含图片(当用户上传评论时检测)0:无图片 1:有图片")
    private Integer isHavingImg;

    /**
     * 位置评分
     */
    @TableField(value = "position_score")
    @ApiModelProperty(value="位置评分")
    private Integer positionScore;

    /**
     * 设施评分
     */
    @TableField(value = "facilities_score")
    @ApiModelProperty(value="设施评分")
    private Integer facilitiesScore;

    /**
     * 服务评分
     */
    @TableField(value = "service_score")
    @ApiModelProperty(value="服务评分")
    private Integer serviceScore;

    /**
     * 卫生评分
     */
    @TableField(value = "hygiene_score")
    @ApiModelProperty(value="卫生评分")
    private Integer hygieneScore;

    /**
     * 综合评分
     */
    @TableField(value = "score")
    @ApiModelProperty(value="综合评分")
    private Integer score;

    /**
     * 出游类型
     */
    @TableField(value = "trip_mode")
    @ApiModelProperty(value="出游类型")
    private Long tripMode;

    /**
     * 是否满意（0：有待改善 1：值得推荐）
     */
    @TableField(value = "is_ok")
    @ApiModelProperty(value="是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;

    @TableField(value = "creation_date")
    @ApiModelProperty(value="")
    private Date creationDate;

    @TableField(value = "created_by")
    @ApiModelProperty(value="")
    private Long createdBy;

    @TableField(value = "modify_date")
    @ApiModelProperty(value="")
    private Date modifyDate;

    @TableField(value = "modified_by")
    @ApiModelProperty(value="")
    private Long modifiedBy;

    /**
     * 逻辑删除（0:未删除；1：删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除（0:未删除；1：删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_HOTEL_ID = "hotel_id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_PRODUCT_TYPE = "product_type";

    public static final String COL_CONTENT = "content";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_HAVING_IMG = "is_having_img";

    public static final String COL_POSITION_SCORE = "position_score";

    public static final String COL_FACILITIES_SCORE = "facilities_score";

    public static final String COL_SERVICE_SCORE = "service_score";

    public static final String COL_HYGIENE_SCORE = "hygiene_score";

    public static final String COL_SCORE = "score";

    public static final String COL_TRIP_MODE = "trip_mode";

    public static final String COL_IS_OK = "is_ok";

    public static final String COL_CREATION_DATE = "creation_date";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_MODIFY_DATE = "modify_date";

    public static final String COL_MODIFIED_BY = "modified_by";

    public static final String COL_IS_DELETED = "is_deleted";
}