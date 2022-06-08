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
    * 实时库存表
    */
@ApiModel(value="com-cskt-entity-HotelTempStore")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "itrip_hotel_temp_store")
public class HotelTempStore implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    @TableField(value = "hotel_id")
    @ApiModelProperty(value="")
    private Integer hotelId;

    /**
     * 商品id
     */
    @TableField(value = "room_id")
    @ApiModelProperty(value="商品id")
    private Long roomId;

    /**
     * 记录时间
     */
    @TableField(value = "record_date")
    @ApiModelProperty(value="记录时间")
    private Date recordDate;

    /**
     * 库存
     */
    @TableField(value = "store")
    @ApiModelProperty(value="库存")
    private Integer store;

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

    public static final String COL_ROOM_ID = "room_id";

    public static final String COL_RECORD_DATE = "record_date";

    public static final String COL_STORE = "store";

    public static final String COL_CREATION_DATE = "creation_date";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_MODIFY_DATE = "modify_date";

    public static final String COL_MODIFIED_BY = "modified_by";

    public static final String COL_IS_DELETED = "is_deleted";
}