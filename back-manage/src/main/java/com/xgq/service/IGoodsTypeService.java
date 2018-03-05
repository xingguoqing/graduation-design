package com.xgq.service;

import dto.PageDto;
import com.xgq.po.GoodsTypePo;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午9:50
 */
public interface IGoodsTypeService {

    List<GoodsTypePo> selPageGoodsType(PageDto pageDto, String keyword);

    Long selCount(String keyword);

    List<GoodsTypePo> selGoodsType(String keyword);

    GoodsTypePo addGoodsType(String typeName);
}
