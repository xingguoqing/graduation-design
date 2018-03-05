package com.xgq.dao.impl;

import dto.PageDto;
import com.xgq.dao.IGoodsTypeDao;
import com.xgq.mapper.IGoodsTypeMapper;
import com.xgq.po.GoodsTypePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午10:47
 */
@Repository
public class GoodsTypeDaoImpl implements IGoodsTypeDao {


    @Autowired
    IGoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsTypePo> selPageGoodsType(PageDto pageDto, String keyword) {
        return goodsTypeMapper.selPageGoodsType(pageDto,keyword);
    }

    @Override
    public Long selCount(String keyword) {
        return goodsTypeMapper.selCount(keyword);
    }

    @Override
    public List<GoodsTypePo> selGoodsType(String keyword) {
        return goodsTypeMapper.selGoodsType(keyword);
    }

    @Override
    public void addGoodsType(GoodsTypePo goodsTypePo) {
        goodsTypeMapper.addGoodsType(goodsTypePo);
    }

    @Override
    public GoodsTypePo getByTypeName(String typeName) {
        return goodsTypeMapper.getByTypeName(typeName);
    }
}
