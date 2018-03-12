package com.xgq.dao.impl;

import com.xgq.dao.IGoodsDao;
import com.xgq.dto.GoodPicDto;
import com.xgq.dto.GoodPriceDto;
import com.xgq.mapper.IGoodsMapper;
import com.xgq.po.GoodsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午3:31
 */
@Repository
public class GoodsDaoImpl implements IGoodsDao{


    @Autowired
    private IGoodsMapper goodsMapper;


    @Override
    public void addGood(GoodsPo goodsPo) {
        goodsMapper.addGood(goodsPo);
    }

    @Override
    public GoodsPo selByName(String goodName) {
        return goodsMapper.selByName(goodName);
    }

    @Override
    public void updateGoodTypeByName(Long id, String goodName) {
        goodsMapper.updateGoodTypeByName(id,goodName);
    }

    @Override
    public void updateGoodDescByName(String goodDesc, String goodName) {
        goodsMapper.updateGoodDescByName(goodDesc,goodName);
    }

    @Override
    public void updateGoodKeyByName(String goodKey, String goodName) {
        goodsMapper.updateGoodKeyByName(goodKey,goodName);
    }

    @Override
    public void updateGoodPriceByName(GoodPriceDto goodPriceDto) {
        goodsMapper.updateGoodPriceByName(goodPriceDto);
    }

    @Override
    public void updatePic(GoodPicDto goodPicDto) {
        goodsMapper.updatePic(goodPicDto);
    }
}
