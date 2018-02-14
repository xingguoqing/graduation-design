package com.xgq.controller;

import com.xgq.po.AddrPo;
import com.xgq.service.IAddrService;
import dto.PageDto;
import dto.PageResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;
import util.valid.PageUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:42
 */
@Api(value = "地址管理相关接口", tags = "AddrController")
@RestController
@RequestMapping("/public/addr")
public class AddrController {


    @Autowired
    IAddrService addrService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddrController.class);

    @ApiOperation(value = "分页查询地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selPageAddrs", method = RequestMethod.GET)
    public ICommonResponse selPageAddrs(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam("userId") Long userId) {
        try {
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            PageResultDto pageResultDto = addrService.selPageAddrs(userId, pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "条件分页查询地址信息失败");
        }
    }

    @ApiOperation(value = "设置地址为默认")
    @ApiImplicitParam(name = "addrId", dataType = "long", paramType = "query", value = "地址id", required = true)
    @RequestMapping(value = "/setDefaultAddr", method = RequestMethod.GET)
    public ICommonResponse setDefaultAddr(@RequestParam("addrId") Long addrId) {
        try {
            addrService.setDefaultAddr(addrId);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "设置默认地址失败");
        }
    }

    @ApiOperation(value = "删除地址")
    @ApiImplicitParam(name = "addrId", dataType = "long", paramType = "query", value = "地址id", required = true)
    @RequestMapping(value = "/delAddr", method = RequestMethod.GET)
    public ICommonResponse delAddr(@RequestParam("addrId") Long addrId) {
        try {
            addrService.delAddr(addrId);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "删除地址失败");
        }
    }

    @ApiOperation(value = "修改地址")
    @ApiImplicitParam(name = "addrPo", dataType = "AddrPo", paramType = "body", value = "地址信息", required = true)
    @RequestMapping(value = "/updateAddr", method = RequestMethod.POST)
    public ICommonResponse updateAddr(@RequestBody AddrPo addrPo) {
        try {
            addrService.updateAddr(addrPo);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "修改地址失败");
        }
    }

    @ApiOperation(value = "添加地址")
    @ApiImplicitParam(name = "addrPo", dataType = "AddrPo", paramType = "body", value = "地址信息", required = true)
    @RequestMapping(value = "/addAddr", method = RequestMethod.POST)
    public ICommonResponse addAddr(HttpServletRequest request,@RequestBody AddrPo addrPo) {
        try {
            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
            addrPo.setUserId(userId);
            addrService.addAddr(addrPo);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "添加失败");
        }
    }
}

