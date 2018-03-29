package com.xgq.controller;

import com.xgq.errorcode.GoodsErrorCode;
import com.xgq.errorcode.GoodsTypeErrorCode;
import dto.PageDto;
import dto.PageResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.xgq.po.GoodsTypePo;
import com.xgq.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;
import util.valid.PageUtil;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/5 上午9:48
 */
@RestController
@RequestMapping("/goodsType")
@Api(value = "商品分类相关接口", tags = "GoodsTypeController")
@Slf4j
public class GoodsTypeController {


    @Autowired
    IGoodsTypeService goodsTypeService;


    @RequestMapping(value = "/selPageGoodsType", method = RequestMethod.GET)
    public ICommonResponse selPageGoodsType(@RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize, @RequestParam(value = "keyword", required = false) String keyword) {
        try {
            log.info("开始分页查询商品分类,page:{},rows:{},keyword:{}",pageNum,pageSize,keyword);
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            List<GoodsTypePo> goodsTypePoList = goodsTypeService.selPageGoodsType(pageDto, keyword);
            Long count = goodsTypeService.selCount(keyword);
            PageResultDto resultDto = new PageResultDto();
            resultDto.setRows(goodsTypePoList);
            resultDto.setTotal(count);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, resultDto);
        } catch (Exception e) {
            log.error("条件分页查询商品分类信息失败,失败原因:{}",e.getMessage());
            return BusinessRuntimeException.responseException(e, "条件分页查询商品分类信息失败");
        }
    }

    @RequestMapping(value = "/selGoodsType", method = RequestMethod.GET)
    public ICommonResponse selGoodsType(@RequestParam(value = "keyword", required = false) String keyword) {
        try {
            log.info("开始查询商品分类,keyword:{}",keyword);
            List<GoodsTypePo> goodsTypePoList = goodsTypeService.selGoodsType(keyword);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, goodsTypePoList);
        } catch (Exception e) {
            log.error("查询商品分类信息失败,失败原因:{}",e.getMessage());
            return BusinessRuntimeException.responseException(e, "查询商品分类信息失败");
        }
    }

    @RequestMapping(value = "/addGoodsType",method = RequestMethod.GET)
    public ICommonResponse addGoodsType(@RequestParam(value = "typeName") String typeName){
        try{
            log.info("开始添加商品分类,typeName:{}",typeName);
            GoodsTypePo goodsTypePo = goodsTypeService.selByTypeName(typeName);
            if(goodsTypePo !=null){
                return new CommonResponse(GoodsTypeErrorCode.NAME_REPEAT);
            }
            goodsTypeService.addGoodsType(typeName);
            log.info("添加商品分类成功，商品分类id:{}",goodsTypePo.getId());
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        }catch (Exception e){
            log.error("添加商品分类失败失败,失败原因:{}",e.getMessage());
            return BusinessRuntimeException.responseException(e, "添加商品分类失败失败");
        }
    }


}
