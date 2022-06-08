package com.cskt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cskt.entity.OrderLinkUser;
import com.cskt.mapper.OrderLinkUserMapper;
import com.cskt.service.OrderLinkUserService;
import org.springframework.stereotype.Service;

@Service
public class OrderLinkUserServiceImpl extends ServiceImpl<OrderLinkUserMapper, OrderLinkUser> implements OrderLinkUserService{

}
