package com.xgq.controller;

import com.xgq.dto.UserDto;
import com.xgq.enums.RoleEnum;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.service.IUserService;
import dto.PageDto;
import dto.PageResultDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;
import util.valid.PageUtil;
import util.valid.StatusUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午1:57
 */
@RestController
@RequestMapping("/public/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "【管理员】条件分页查询用户(包括普通用户和维修员)")
    @ApiResponse(message = "条件分页查询用户结果", code = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", dataType = "int", value = "分页页数", required = true),
            @ApiImplicitParam(name = "rows", paramType = "query", dataType = "int", value = "分页大小", required = true),
            @ApiImplicitParam(name = "userDto", paramType = "body", dataType = "UserDto", value = "查询条件", required = true)})
    @RequestMapping(value = "/select/{type}", method = RequestMethod.POST)
    public ICommonResponse selectUsers(@PathVariable("type") Long type,
                                        @RequestParam(value = "page") int pageNum,
                                       @RequestParam(value = "rows") int pageSize) {

        try {
            UserDto userDto = new UserDto();
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            PageResultDto pageResultDto;
            if(RoleEnum.ORDINARY_USER.getCode().equals(type)){
                pageResultDto = userService.selectPageUsers(type,userDto, pageDto);
            }else if(RoleEnum.REPAIR_PERSONNEL.getCode().equals(type)){
                pageResultDto = userService.selectPageUsers(type,userDto, pageDto);
            }else{
                return new CommonResponse(CommonRespCodeEnum.FAIL_CODE, "用户类型错误");
            }
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "条件分页查询用户失败");
        }
    }

    @ApiOperation(value = "【管理员】改变用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", paramType = "path", dataType = "String", value = "变更后的状态", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户账号", required = true)
    })
    @RequestMapping(value = "/changeUserStatus/{status}", method = RequestMethod.POST)
    public ICommonResponse changeUserStatus(@PathVariable(value = "status") String stauts, @RequestParam Long id) {

        try {
            List<Long> ids = new ArrayList<Long>();
            ids.add(id);
            StatusUtil.validStatus(stauts);
            userService.updateStatusByUserIds(ids, stauts);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "改变用户状态失败");
        }
    }

    @ApiOperation(value = "【管理员】根据用户编号查询用户")
    @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "Long", value = "用户id", required = true)
    @RequestMapping(value = "/getUserByUserId", method = RequestMethod.GET)
    public ICommonResponse getUserByUserId(@RequestParam(value = "id") Long id) {

        try {
            UserDto userDto = userService.getUserByUserId(id);
            if (userDto == null) {
                return new CommonResponse(UserErrorCode.USER_NOT_EXIST);
            }
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, userDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "根据用户编号查询用户失败");
        }
    }
}
