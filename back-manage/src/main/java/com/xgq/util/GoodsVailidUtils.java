package com.xgq.util;

import com.xgq.dto.GoodsDto;
import com.xgq.errorcode.GoodsErrorCode;
import com.xgq.po.GoodsPo;
import com.xgq.po.GoodsTypePo;
import com.xgq.service.IGoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.exception.BusinessRuntimeException;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午2:47
 */
@Component
@Slf4j
public class GoodsVailidUtils {

    @Autowired
    IGoodsTypeService goodsTypeService;

    public void validAddParams(GoodsDto goodsDto) {
        log.info("开始校验商品信息参数:{}",goodsDto.toString());
        if (StringUtils.isEmpty(goodsDto.getTypeName()) || !isLegalGoodsType(goodsDto.getTypeName())) {
            BusinessRuntimeException.wrapBusiException(GoodsErrorCode.GOOD_TYPE_UNLEGAL);
        }
        if (StringUtils.isEmpty(goodsDto.getGoodKey())) {
            BusinessRuntimeException.wrapBusiException(GoodsErrorCode.GOOD_KEY_UNLEGAL);
        }
        if (StringUtils.isEmpty(goodsDto.getGoodName())) {
            BusinessRuntimeException.wrapBusiException(GoodsErrorCode.GOOD_NAME_UNLEGAL);
        }
        log.info("校验商品信息参数通过");
    }


    /**
     * 校验商品分类是否合法
     *
     * @param typeName 要校验的商品分类
     * @return
     */
    public boolean isLegalGoodsType(String typeName) {
        GoodsTypePo goodsType = goodsTypeService.selByTypeName(typeName);
        if(goodsType == null){
            return false;
        }
        return true;
    }

}
