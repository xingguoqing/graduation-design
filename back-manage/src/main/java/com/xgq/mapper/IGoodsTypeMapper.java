package com.xgq.mapper;

import dto.PageDto;
import com.xgq.po.GoodsTypePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午10:53
 */
@Mapper
public interface IGoodsTypeMapper {

    List<GoodsTypePo> selPageGoodsType(@Param("page") PageDto pageDto, @Param("keyword") String keyword);

    Long selCount(@Param("keyword") String keyword);

    List<GoodsTypePo> selGoodsType(@Param("keyword") String keyword);

    void addGoodsType(@Param("goodsTypePo") GoodsTypePo goodsTypePo);

    GoodsTypePo getByTypeName(@Param("typeName") String typeName);
}
