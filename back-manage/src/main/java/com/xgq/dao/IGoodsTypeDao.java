package com.xgq.dao;

import dto.PageDto;
import com.xgq.po.GoodsTypePo;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午10:46
 */
public interface IGoodsTypeDao {
    List<GoodsTypePo> selPageGoodsType(PageDto pageDto, String keyword);

    Long selCount(String keyword);

    List<GoodsTypePo> selGoodsType(String keyword);

    void addGoodsType(GoodsTypePo goodsTypePo);

    GoodsTypePo getByTypeName(String typeName);

}
