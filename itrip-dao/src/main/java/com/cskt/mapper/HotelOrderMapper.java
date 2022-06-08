package com.cskt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskt.entity.HotelOrder;
import com.cskt.vo.ListHotelOrderVO;
import com.cskt.vo.PersonalOrderRoomVO;
import com.cskt.vo.SearchOrderCondition;
import com.cskt.vo.ValidateRoomStoreCondition;

import java.util.List;

public interface HotelOrderMapper extends BaseMapper<HotelOrder> {
    /**
     * 调用存储过程 库存检查
     */
    void preFlushStore(ValidateRoomStoreCondition condition);

    /**
     * 调用存储过程 查询实时库存表的房间库存数
     */
    int searchTempStore(ValidateRoomStoreCondition condition);

    /**
     * 调用存储过程 查询已下单但未支付的房间数
     */
    int searchUnPayStore(ValidateRoomStoreCondition condition);

    /**
     * 根据 orderId 查询个人订单详情-房型信息
     * @param orderId
     * @return
     */
    PersonalOrderRoomVO selectPersonalOrderRoomVOByOrderId(Long orderId);

    /**
     * 订单列表分页查询
     * @param condition
     * @return
     */
    List<ListHotelOrderVO> selectOrderVOListByCondition(SearchOrderCondition condition);

    /**
     * 订单列表分页查询数量
     * @param condition
     * @return
     */
    Integer selectOrderVOCountByCondition(SearchOrderCondition condition);
}