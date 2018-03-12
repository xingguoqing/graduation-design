package com.xgq.service.impl;

import com.xgq.dao.IGoodsTypeDao;
import com.xgq.errorcode.GoodsTypeErrorCode;
import com.xgq.service.IGoodsTypeService;
import dto.PageDto;
import com.xgq.po.GoodsTypePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.exception.BusinessRuntimeException;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午9:50
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService {


    @Autowired
    IGoodsTypeDao goodsTypeDao;

    @Override
    public List<GoodsTypePo> selPageGoodsType(PageDto pageDto, String keyword) {
        return goodsTypeDao.selPageGoodsType(pageDto,keyword);
    }

    @Override
    public Long selCount(String keyword) {
        return goodsTypeDao.selCount(keyword);
    }

    @Override
    public List<GoodsTypePo> selGoodsType(String keyword) {
        return goodsTypeDao.selGoodsType(keyword);
    }

    @Override
    public GoodsTypePo addGoodsType(String typeName) {

        GoodsTypePo goodsTypePo = goodsTypeDao.getByTypeName(typeName);
        if(goodsTypePo!=null){
            BusinessRuntimeException.wrapBusiException(GoodsTypeErrorCode.NAME_REPEAT);
        }
        goodsTypePo = new GoodsTypePo();
        goodsTypePo.setName(typeName);
        goodsTypeDao.addGoodsType(goodsTypePo);
        return goodsTypePo;
    }

    @Override
    public List<GoodsTypePo> selAllGoodsType() {
        return goodsTypeDao.selGoodsType(null);
    }

    @Override
    public GoodsTypePo selByTypeName(String typeName) {
        return goodsTypeDao.getByTypeName(typeName);
    }
}
