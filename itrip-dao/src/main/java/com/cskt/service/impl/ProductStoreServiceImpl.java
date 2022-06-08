package com.cskt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cskt.entity.ProductStore;
import com.cskt.mapper.ProductStoreMapper;
import com.cskt.service.ProductStoreService;
import org.springframework.stereotype.Service;

@Service
public class ProductStoreServiceImpl extends ServiceImpl<ProductStoreMapper, ProductStore> implements ProductStoreService{

}
