package com.xgq.mapper;

import com.xgq.dto.GoodPicDto;
import com.xgq.dto.GoodPriceDto;
import com.xgq.po.GoodsPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午3:33
 */
@Mapper
public interface IGoodsMapper {

    void addGood(@Param("goodsPo") GoodsPo goodsPo);

    GoodsPo selByName(@Param("goodName") String goodName);

    void updateGoodTypeByName(@Param("id") Long id,@Param("goodName") String goodName);

    void updateGoodDescByName(@Param("goodDesc") String goodDesc,@Param("goodName") String goodName);

    void updateGoodKeyByName(@Param("goodKey") String goodKey,@Param("goodName") String goodName);

    void updateGoodPriceByName(@Param("goodPriceDto") GoodPriceDto goodPriceDto);

    void updatePic(@Param("goodPicDto") GoodPicDto goodPicDto);
}
