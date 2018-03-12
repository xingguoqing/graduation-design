package com.xgq.controller;

import com.alibaba.fastjson.JSONObject;
import com.xgq.dto.GoodPicDto;
import com.xgq.dto.GoodPriceDto;
import com.xgq.dto.GoodsDto;
import com.xgq.errorcode.GoodsErrorCode;
import com.xgq.errorcode.GoodsTypeErrorCode;
import com.xgq.po.GoodsPo;
import com.xgq.po.GoodsTypePo;
import com.xgq.service.IGoodsService;
import com.xgq.service.IGoodsTypeService;
import com.xgq.util.GoodsVailidUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * @date 2018/3/8 下午8:47
 */
@RestController
@RequestMapping("/goods")
@Api(value = "商品信息相关接口", tags = "GoodsController")
@Slf4j
public class GoodsController {


    @Autowired
    private GoodsVailidUtils goodsVailidUtils;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService goodsTypeService;


    @RequestMapping(value = "/addGood", method = RequestMethod.POST)
    public ICommonResponse addGood(@RequestBody GoodsDto goodsDto) {

        try {
            log.info("添加商品信息");
            goodsVailidUtils.validAddParams(goodsDto);
            GoodsPo goodsPo1 = goodsService.selByName(goodsDto.getGoodName());
            if(goodsPo1 != null){
                return new CommonResponse(GoodsErrorCode.GOOD_HAS_EXIST);
            }
            goodsService.addGood(parseToFGoodsPo(goodsDto));
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("添加商品信息失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "添加商品信息失败");
        }
    }

    @RequestMapping(value = "/updateGoodTypeByName", method = RequestMethod.POST)
    public ICommonResponse updateGoodTypeByName(@RequestParam("typeName") String typeName,@RequestParam("goodName") String goodName) {

        try {
            log.info("修改商品所属分类，typeName:{},goodName:{}",typeName,goodName);
            GoodsPo goodsPo = goodsService.selByName(goodName);
            if(goodsPo == null){
                return new CommonResponse(GoodsErrorCode.GOOD_NOT_EXIST);
            }
            GoodsTypePo goodsTypePo = goodsTypeService.selByTypeName(typeName);
            if(goodsTypePo == null){
                return new CommonResponse(GoodsTypeErrorCode.GOOD_TYPE_NOT_EXIST);
            }
            goodsService.updateGoodTypeByName(goodsTypePo.getId(),goodName);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("修改商品所属分类失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改商品所属分类失败");
        }
    }

    @RequestMapping(value = "/updateGoodKeyByName", method = RequestMethod.POST)
    public ICommonResponse updateGoodKeyByName(@RequestParam("goodKey") String goodKey,@RequestParam("goodName") String goodName) {

        try {
            log.info("修改商品描述,goodKey:{},goodName:{}",goodKey,goodName);
            GoodsPo goodsPo = goodsService.selByName(goodName);
            if(goodsPo == null){
                return new CommonResponse(GoodsErrorCode.GOOD_NOT_EXIST);
            }
            goodsService.updateGoodKeyByName(goodKey,goodName);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("修改商品描述失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改商品描述失败");
        }
    }


    @RequestMapping(value = "/updateGoodDescByName", method = RequestMethod.POST)
    public ICommonResponse updateGoodDescByName(@RequestParam("goodDesc") String goodDesc,@RequestParam("goodName") String goodName) {

        try {
            log.info("修改商品描述,goodDesc:{},goodName:{}",goodDesc,goodName);
            GoodsPo goodsPo = goodsService.selByName(goodName);
            if(goodsPo == null){
                return new CommonResponse(GoodsErrorCode.GOOD_NOT_EXIST);
            }
            goodsService.updateGoodDescByName(goodDesc,goodName);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("修改商品描述失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改商品描述失败");
        }
    }

    @RequestMapping(value = "/updateGoodPriceByName", method = RequestMethod.POST)
    public ICommonResponse updateGoodPriceByName(@RequestBody GoodPriceDto goodPriceDto) {

        try {
            log.info("修改商品价格,{}", JSONObject.toJSONString(goodPriceDto));
            GoodsPo goodsPo = goodsService.selByName(goodPriceDto.getGoodName());
            if(goodsPo == null){
                return new CommonResponse(GoodsErrorCode.GOOD_NOT_EXIST);
            }
            goodsService.updateGoodPriceByName(goodPriceDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("修改商品价格失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改商品价格失败");
        }
    }

    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public ICommonResponse updatePic(@RequestBody GoodPicDto goodPicDto) {

        try {
            log.info("修改商品图片,{}", JSONObject.toJSONString(goodPicDto));
            GoodsPo goodsPo = goodsService.selByName(goodPicDto.getGoodName());
            if(goodsPo == null){
                return new CommonResponse(GoodsErrorCode.GOOD_NOT_EXIST);
            }
            goodsService.updatePic(goodPicDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            log.error("修改商品价格失败,失败原因:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改商品价格失败");
        }
    }




    private GoodsPo parseToFGoodsPo(GoodsDto goodsDto){
        GoodsPo goodsPo = new GoodsPo();
        goodsPo.setTypeId(goodsTypeService.selByTypeName(goodsDto.getTypeName()).getId());
        goodsPo.setGoodDesc(goodsDto.getGoodDesc());
        goodsPo.setGoodKey(goodsDto.getGoodKey());
        goodsPo.setGoodName(goodsDto.getGoodName());
        goodsPo.setPic1Path(goodsDto.getPic1Path());
        goodsPo.setPic2Path(goodsDto.getPic2Path());
        goodsPo.setPic3Path(goodsDto.getPic3Path());
        goodsPo.setPic4Path(goodsDto.getPic4Path());
        goodsPo.setPic5Path(goodsDto.getPic5Path());
        goodsPo.setPic6Path(goodsDto.getPic6Path());
        goodsPo.setGroupPrice(goodsDto.getGroupPrice());
        goodsPo.setCostPrice(goodsDto.getCostPrice());
        goodsPo.setProxyPrice(goodsDto.getProxyPrice());
        goodsPo.setSinglePrice(goodsDto.getSinglePrice());
        return goodsPo;
    }





}
