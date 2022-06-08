package com.cskt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskt.entity.HotelRoom;
import com.cskt.vo.HotelRoomCondition;
import com.cskt.vo.HotelRoomVO;

import java.util.List;

public interface HotelRoomMapper extends BaseMapper<HotelRoom> {    /**
 * 根据条件查询酒店房型
 * @param condition
 * @return
 */
List<HotelRoomVO> selectHotelRoomVOByCondition(HotelRoomCondition condition);


}