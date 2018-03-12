package com.xgq.service.impl;

import com.xgq.dao.IGoodsDao;
import com.xgq.dto.GoodPicDto;
import com.xgq.dto.GoodPriceDto;
import com.xgq.po.GoodsPo;
import com.xgq.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午3:19
 */
@Service
public class GoodsServiceImpl implements IGoodsService {


    @Autowired
    private IGoodsDao goodsDao;


    @Override
    public void addGood(GoodsPo goodsPo) {
        goodsDao.addGood(goodsPo);
    }

    @Override
    public GoodsPo selByName(String goodName) {
        return goodsDao.selByName(goodName);
    }

    @Override
    public void updateGoodTypeByName(Long id, String goodName) {
        goodsDao.updateGoodTypeByName(id, goodName);
    }

    @Override
    public void updateGoodDescByName(String goodDesc, String goodName) {
        goodsDao.updateGoodDescByName(goodDesc,goodName);
    }

    @Override
    public void updateGoodKeyByName(String goodKey, String goodName) {
        goodsDao.updateGoodKeyByName(goodKey,goodName);
    }

    @Override
    public void updateGoodPriceByName(GoodPriceDto goodPriceDto) {
        goodsDao.updateGoodPriceByName(goodPriceDto);
    }

    @Override
    public void updatePic(GoodPicDto goodPicDto) {
        goodsDao.updatePic(goodPicDto);
    }
}
