package com.xgq.controller;

import com.xgq.dto.JobDto;
import com.xgq.dto.UserDto;
import com.xgq.enums.RoleEnum;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.po.UserPo;
import com.xgq.po.UserRolePo;
import com.xgq.service.IJobService;
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

import javax.servlet.http.Cookie;
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

    @Autowired
    private IJobService jobService;


    @ApiOperation(value = "【用户】登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", paramType = "query", dataType = "String", value = "用户手机号", required = true),
            @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "密码", required = true)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ICommonResponse login(HttpServletResponse response , @RequestParam String userPhone, @RequestParam String password) {
        try {
            UserUtil.vailUserPhone(userPhone);
            UserDto userDto = userService.login(userPhone, password);
            if(userDto.getUserStatus().equals("N")){
                BusinessRuntimeException.wrapBusiException(UserErrorCode.USER_NOT_ACTIVATION);
            }
            String jwt = JwtUtils.createJwt(userDto.getUserId());
//            Cookie cookie = new Cookie("sign", jwt);
//            cookie.setMaxAge(30 * 60);//30min有效
//            response.addCookie(cookie);
            response.setHeader("sign",jwt);
//            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Expose-Headers","sign");
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, userDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "登录失败");
        }
    }

    @ApiOperation(value = "【用户】注册用户")
    @ApiImplicitParam(name = "userPo", paramType = "body", dataType = "UserPo", value = "用户信息", required = true)
    @RequestMapping(value = "/addUser/{type}", method = RequestMethod.POST)
    @Transactional
    public ICommonResponse addUser(@PathVariable("type") String type,@RequestBody UserPo userPo) {

        try {
            LOGGER.info("【用户】注册用户");
            UserUtil.vailUserParams(userPo);
            userService.addUser(userPo,type);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            LOGGER.error("注册用户失败:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "注册用户失败");
        }
    }


    @ApiOperation(value = "【用户/维修员】更新用户手机号")
    @ApiImplicitParam(name = "phone", paramType = "query", dataType = "String", value = "修改后的手机号", required = true)
    @RequestMapping(value = "/updateUserPhone", method = RequestMethod.GET)
    public ICommonResponse updateUserPhone(@RequestParam String phone) {

        try {
            LOGGER.info("【用户/维修员】更新用户手机号");
            Long id = UserUtil.getUserId();
            UserUtil.vailUserPhone(phone);
            userService.updateUserPhone(phone, id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            LOGGER.error("更新用户手机号失败:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "更新用户手机号失败");
        }
    }

    @ApiOperation(value = "【用户】修改用户密码")
    @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "用户密码", required = true)
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public ICommonResponse updatePassword(@RequestParam String password) {

        try {
            LOGGER.info("【用户】修改用户密码");
            Long id = UserUtil.getUserId();
            userService.updatePassword(password, id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            LOGGER.error("修改用户密码失败:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改用户密码失败");
        }
    }

    @ApiOperation(value = "【用户】查询用户")
    @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "用户密码", required = true)
    @RequestMapping(value = "/selUserById", method = RequestMethod.GET)
    public ICommonResponse selUserById(HttpServletRequest request) {

        try {
            String jwt = request.getHeader("sign");
            Long id = JwtUtils.parseJWT(jwt);
            UserPo userPo = userService.selUserById(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,userPo);
        } catch (Exception e) {
            LOGGER.error("修改用户密码失败:{}", e.getMessage());
            return BusinessRuntimeException.responseException(e, "修改用户密码失败");
        }
    }


//    @ApiOperation(value = "【用户】查看提交的报修")
////    @ApiImplicitParam(name = "password", paramType = "query", dataType = "String", value = "用户密码", required = true)
//    @RequestMapping(value = "/selSubJob", method = RequestMethod.POST)
//    public ICommonResponse selSubJob(HttpServletRequest request, @RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
//
//        try {
//            JobDto jobDto = new JobDto();
//            PageUtil.validParams(pageNum, pageSize);
//            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
//            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
//            jobDto.setSubUserId(userId);
//            PageResultDto pageResultDto = jobService.selSubJob(userId, pageDto);
//            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,pageResultDto);
//        } catch (Exception e) {
//            LOGGER.error("修改用户密码失败:{}", e.getMessage());
//            return BusinessRuntimeException.responseException(e, "修改用户密码失败");
//        }
//    }


}
