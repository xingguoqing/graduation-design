package com.xgq.controller;

import com.xgq.dto.MenuDto;
import com.xgq.service.IMenuService;
import com.xgq.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;

import java.util.List;


/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:15
 */
@Api(value = "菜单管理接口",tags = "MenuController")
@RestController
@RequestMapping("/public/menu")
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);


    @Autowired
    IMenuService menuService;

    @ApiOperation(value = "获取用户菜单")
    @RequestMapping(value = "/selUserMenus",method = RequestMethod.GET)
    public ICommonResponse selUserMenus(){
        try {
            Long userId = UserUtil.getUserId();
            List<MenuDto> menuPoList = menuService.selUserMenus(userId);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,menuPoList);
        }catch (Exception e){
            return BusinessRuntimeException.responseException(e,"获取用户菜单失败");
        }

    }


}
