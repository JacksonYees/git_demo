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

/**
    * 订单支付完成后，系统需对该订单进行后续处理，如减库存等。处理完成后，删除此表中的订单记录
    */
@ApiModel(value="com-cskt-entity-TradeEnds")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "itrip_trade_ends")
public class TradeEnds implements Serializable {
    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="订单ID")
    private Long id;

    /**
     * 订单编号(注意非支付宝交易编号tradeNo)
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value="订单编号(注意非支付宝交易编号tradeNo)")
    private String orderNo;

    /**
     * 标识字段(默认0：未处理；1：处理中)
     */
    @TableField(value = "flag")
    @ApiModelProperty(value="标识字段(默认0：未处理；1：处理中)")
    private Boolean flag;

    /**
     * 逻辑删除（0:未删除；1：删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除（0:未删除；1：删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_FLAG = "flag";

    public static final String COL_IS_DELETED = "is_deleted";
}