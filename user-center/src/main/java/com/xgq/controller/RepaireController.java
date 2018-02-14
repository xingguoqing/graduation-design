package com.xgq.controller;

import com.xgq.enums.RoleEnum;
import com.xgq.po.UserPo;
import com.xgq.service.IUserService;
import com.xgq.util.UserUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午2:09
 */
@RestController
@RequestMapping("/repaire")
public class RepaireController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

//
//    @ApiOperation(value = "【维修员】注册用户")
//    @ApiImplicitParam(name = "userPo", paramType = "body", dataType = "UserPo", value = "用户信息", required = true)
//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    @Transactional
//    public ICommonResponse addUser(@RequestBody UserPo userPo) {
//
//        try {
//            LOGGER.info("【维修员】注册用户");
//            UserUtil.vailUserParams(userPo);
//            userService.addUser(userPo, RoleEnum.REPAIR_PERSONNEL);
//            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
//        } catch (Exception e) {
//            LOGGER.error("注册用户失败:{}",e.getMessage());
//            return BusinessRuntimeException.responseException(e, "注册用户失败");
//        }
//    }
}
