package com.xgq.controller;

import com.xgq.dto.UserDto;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.po.UserPo;
import com.xgq.service.IUserService;
import com.xgq.util.UserUtil;
import dto.PageDto;
import dto.PageResultDto;
import io.swagger.annotations.*;
import jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.ICommonResponse;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;
import util.valid.PageUtil;
import util.valid.StatusUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 用户相关controller
 */
@Api(value = "用户相关接口", tags = {"UserController"})
@RestController
@RequestMapping(value = "/public/user")
public class UserController {


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


    @ApiOperation(value = "【用户】注册用户")
    @ApiImplicitParam(name = "userPo", paramType = "body", dataType = "UserPo", value = "用户信息", required = true)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @Transactional
    public ICommonResponse addUser(@RequestBody UserPo userPo) {

        try {
            UserUtil.vailUserParams(userPo);
            userService.addUser(userPo);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "注册用户失败");
        }
    }

    @ApiOperation(value = "【用户】更新用户手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", paramType = "query", dataType = "String", value = "修改后的手机号", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "Long", value = "用户id", required = true)
    })
    @RequestMapping(value = "/updateUserPhone", method = RequestMethod.GET)
    public ICommonResponse updateUserPhone(@RequestParam String phone, @RequestParam Long id) {

        try {
            UserUtil.vailUserPhone(phone);
            userService.updateUserPhone(phone, id);

            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "更新用户手机号失败");
        }
    }

    @ApiOperation(value = "【用户】修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "用户密码", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "Long", value = "用户id", required = true)
    })
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public ICommonResponse updatePassword(@RequestParam String password, @RequestParam Long id) {

        try {
            userService.updatePassword(password, id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "修改用户密码失败");
        }
    }


    @ApiOperation(value = "【用户】登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", paramType = "query", dataType = "String", value = "用户手机号", required = true),
            @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "密码", required = true)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ICommonResponse login(HttpServletResponse response, @RequestParam String userPhone, @RequestParam String password) {
        try {
            UserUtil.vailUserPhone(userPhone);
            UserDto userDto = userService.login(userPhone, password);
            //创建JWT
            String jwt = JwtUtils.createJwt(userDto.getId());
            response.addHeader("sign",jwt);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, userDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "登录失败");
        }
    }


    @ApiOperation(value = "【维修员】注册维修员")
    @ApiImplicitParam(name = "userPo", paramType = "body", dataType = "UserPo", value = "w维修员信息", required = true)
    @RequestMapping(value = "/addRepaire", method = RequestMethod.POST)
    @Transactional
    public ICommonResponse addRepaire(@RequestBody UserPo userPo) {

        try {
            UserUtil.vailUserParams(userPo);
            userService.addRepaire(userPo);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "注册用户失败");
        }
    }


}
