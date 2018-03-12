package com.xgq.dao;

import com.xgq.dto.GoodPicDto;
import com.xgq.dto.GoodPriceDto;
import com.xgq.po.GoodsPo; /**
 * @author xingguoqing
 * @date 2018/3/9 下午3:31
 */
public interface IGoodsDao {


    void addGood(GoodsPo goodsPo);

    GoodsPo selByName(String goodName);

    void updateGoodTypeByName(Long id, String goodName);

    void updateGoodDescByName(String goodDesc, String goodName);

    void updateGoodKeyByName(String goodKey, String goodName);

    void updateGoodPriceByName(GoodPriceDto goodPriceDto);

    void updatePic(GoodPicDto goodPicDto);
}
