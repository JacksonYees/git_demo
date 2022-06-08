package com.cskt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cskt.entity.Hotel;
import com.cskt.vo.SearchDetailHotelVO;
import com.cskt.vo.SearchFacilitiesHotelVO;

import java.util.List;

public interface HotelService extends IService<Hotel>{

    /**
     * 根据酒店Id查询酒店设施
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO getHotelFacilitiesById(Long hotelId);

    /**
     * 根据酒店id查询酒店政策
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO getHotelPolicyByHotelId(Long hotelId);

    /**
     *根据酒店的id查询酒店的特色和介绍
     * @param hotelId
     * @return
     */
    List<SearchDetailHotelVO> getHotelDetailVOById(Long hotelId);
}
