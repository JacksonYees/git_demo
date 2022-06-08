package com.cskt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cskt.entity.Hotel;
import com.cskt.entity.LabelDic;
import com.cskt.mapper.HotelMapper;
import com.cskt.service.HotelService;
import com.cskt.vo.SearchDetailHotelVO;
import com.cskt.vo.SearchFacilitiesHotelVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService{

    /**
     * 根据酒店Id查询酒店设施
     *
     * @param hotelId
     * @return
     */
    @Override
    public SearchFacilitiesHotelVO getHotelFacilitiesById(Long hotelId) {
            return baseMapper.selectFacilitiesHotelVOByHotelId(hotelId);

    }

    /**
     * 根据酒店id查询酒店政策
     *
     * @param hotelId
     * @return
     */
    @Override
    public SearchFacilitiesHotelVO getHotelPolicyByHotelId(Long hotelId) {
        return baseMapper.selectHotelPolicyByHotelId(hotelId);
    }

    /**
     * 根据酒店的id查询酒店的特色和介绍
     *
     * @param hotelId
     * @return
     */
    @Override
    public List<SearchDetailHotelVO> getHotelDetailVOById(Long hotelId) {
        //根据需求，酒店特色和介绍是分别存储在不同的表里面的，所以需要在代码中处理
        //1、特色需要通过多表联查的方式实现
        List<LabelDic> labelDicList = baseMapper.selectDetailHotelVOByHotelId(hotelId);

        //2、酒店介绍，可以直接查询
        LambdaQueryWrapper<Hotel> hotelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        hotelLambdaQueryWrapper.eq(Hotel::getId, hotelId)
                .select(Hotel::getHotelName, Hotel::getDetails);
        Hotel hotel = this.getOne(hotelLambdaQueryWrapper);

        //数据封装，特色
        List<SearchDetailHotelVO> detailHotelVOList = labelDicList.stream().map(
                labelDic -> {
                    SearchDetailHotelVO searchDetailHotelVO = new SearchDetailHotelVO();
                    searchDetailHotelVO.setDescription(labelDic.getDescription());
                    searchDetailHotelVO.setName(labelDic.getName());
                    return searchDetailHotelVO;
                }
        ).collect(Collectors.toList());

        //将介绍封装进来
        SearchDetailHotelVO searchDetailHotelVO = new SearchDetailHotelVO();
        searchDetailHotelVO.setName("酒店介绍");
        searchDetailHotelVO.setDescription(hotel.getDetails());
        detailHotelVOList.add(searchDetailHotelVO);

        return detailHotelVOList;
    }
}
