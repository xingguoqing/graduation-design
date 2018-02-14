package com.xgq.controller;

import com.xgq.dto.UserDto;
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

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午1:57
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "【管理员】条件分页查询用户")
    @ApiResponse(message = "条件分页查询用户结果", code = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", paramType = "query", dataType = "int", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", paramType = "query", dataType = "int", value = "分页大小", required = true),
            @ApiImplicitParam(name = "userDto", paramType = "body", dataType = "UserDto", value = "查询条件", required = true)})
    @RequestMapping(value = "/selectUsers", method = RequestMethod.POST)
    public ICommonResponse selectUsers(@RequestParam(value = "pageNum") int pageNum,
                                       @RequestParam(value = "pageSize") int pageSize, @RequestBody UserDto userDto) {

        try {
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            PageResultDto pageResultDto = userService.selectPageUsers(userDto, pageDto);
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
    public ICommonResponse changeUserStatus(@PathVariable(value = "status") String stauts, @RequestBody List<Long> ids) {

        try {
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
