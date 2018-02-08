package com.xgq.controller;

import com.xgq.dto.UserDto;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.po.UserPo;
import com.xgq.service.IUserService;
import com.xgq.util.UserUtil;
import dto.PageDto;
import dto.PageResultDto;
import enums.StatusEnum;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.ICommonResponse;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;
import util.valid.PageUtil;
import util.valid.StatusUtil;

import java.util.List;


/**
 * 用户相关controller
 */
@Api(value = "用户相关接口", tags = {"UserController"})
@RestController
@RequestMapping(value = "/private/user")
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
            return BusinessRuntimeException.responseException(e,"条件分页查询用户失败");
        }
    }


    @ApiOperation(value = "【管理员】改变用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", paramType = "path", dataType = "String", value = "变更后的状态", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户账号", required = true)
    })
    @RequestMapping(value = "/changeUserStatus/{status}", method = RequestMethod.POST)
    public ICommonResponse changeUserStatus(@PathVariable(value = "status") String stauts, @RequestBody List<String> userCodes) {

        try {
            StatusUtil.validStatus(stauts);
            userService.changeUserStatus(userCodes, stauts);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e,"改变用户状态失败");
        }
    }

    @ApiOperation(value = "【管理员】根据用户编号查询用户")
    @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户编号", required = true)
    @RequestMapping(value = "/getUserByUserCode", method = RequestMethod.GET)
    public ICommonResponse getUserByUserCode(@RequestParam(value = "userCode") String userCode) {

        try {
            UserDto userDto = userService.getUserByUserCode(userCode);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, userDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e,"根据用户编号查询用户失败");
        }
    }


    @ApiOperation(value = "【用户】添加用户")
    @ApiImplicitParam(name = "userPo", paramType = "body", dataType = "UserPo", value = "用户信息", required = true)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ICommonResponse addUser(@RequestBody UserPo userPo) {

        try {
            UserUtil.vailUserParams(userPo);
            UserDto userDto = userService.getUserByUserCode(userPo.getUserCode());
            if (userDto != null) {
                return new CommonResponse(UserErrorCode.CODE_TAKE_UP);
            }
            userDto = userService.getUserByUserPhone(userPo.getUserPhone());
            if (userDto != null) {
                return new CommonResponse(UserErrorCode.PHONE_TAKE_UP);
            }
            //默认启用状态
            userPo.setStatus(StatusEnum.ABLE.getCode());
            userService.addUser(userPo);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e,"添加用户失败");
        }
    }

    @ApiOperation(value = "【用户】更新用户手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", paramType = "query", dataType = "String", value = "修改后的手机号", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户编号", required = true)
    })
    @RequestMapping(value = "/updateUserPhone", method = RequestMethod.GET)
    public ICommonResponse updateUserPhone(@RequestParam String phone,@RequestParam String userCode){

        try {
            UserUtil.vailUserPhone(phone);
            UserDto userDto = userService.getUserByUserPhone(phone);
            if (userDto != null) {
                return new CommonResponse(UserErrorCode.PHONE_TAKE_UP);
            }
            userService.updatPhoneByCode(phone,userCode);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        }catch (Exception e){
            return BusinessRuntimeException.responseException(e,"更新用户手机号失败");
        }
    }

    @ApiOperation(value = "【用户】更新用户邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mail", paramType = "query", dataType = "String", value = "修改后的邮箱", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户编号", required = true)
    })
    @RequestMapping(value = "/updateUserMail", method = RequestMethod.GET)
    public ICommonResponse updateUserMail(@RequestParam String mail,@RequestParam String userCode){

        try {
            UserUtil.vailUserMail(mail);
            userService.updateUserMail(mail,userCode);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        }catch (Exception e){
            return BusinessRuntimeException.responseException(e,"更新用户邮箱失败");
        }
    }

    @ApiOperation(value = "【用户】更新用户个性签名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personalProfile", paramType = "query", dataType = "String", value = "用户个性签名", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户编号", required = true)
    })
    @RequestMapping(value = "/updatePersonalProfile", method = RequestMethod.GET)
    public ICommonResponse updatePersonalProfile(@RequestParam String personalProfile,@RequestParam String userCode){

        try {
            userService.updatPersonalProfileByCode(personalProfile,userCode);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        }catch (Exception e){
            return BusinessRuntimeException.responseException(e,"更新用户个性签名失败");
        }
    }

    @ApiOperation(value = "【用户】修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "用户密码", required = true),
            @ApiImplicitParam(name = "userCode", paramType = "query", dataType = "String", value = "用户编号", required = true)
    })
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public ICommonResponse updatePassword(@RequestParam String password,@RequestParam String userCode){

        try {
            userService.updatePassword(password,userCode);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        }catch (Exception e){
            return BusinessRuntimeException.responseException(e,"修改用户密码失败");
        }
    }






}
