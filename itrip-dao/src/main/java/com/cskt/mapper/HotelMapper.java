package com.cskt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskt.entity.Hotel;
import com.cskt.entity.LabelDic;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper extends BaseMapper<Hotel> {
    /**
     * 根据酒店Id查询酒店设施
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO selectFacilitiesHotelVOByHotelId(Long hotelId);

    /**
     * 根据酒店Id查询酒店政策
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO selectHotelPolicyByHotelId(Long hotelId);

    /**
     * 根据酒店Id查询酒店特色
     * @param hotelId
     * @return
     */
    List<LabelDic> selectDetailHotelVOByHotelId(@Param("hotelId") Long hotelId);
}