package com.xgq.controller;

import com.xgq.dto.UserDto;
import com.xgq.service.IUserService;
import dto.PageDto;
import dto.PageResultDto;
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


/**
 * 用户相关controller
 */
@Api(value = "用户相关接口", tags = {"UserController"})
@RestController
@RequestMapping(value = "/user")
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    @ApiOperation(value = "条件分页查询用户")
    @ApiResponse(message = "条件分页查询用户结果", code = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", paramType = "query", dataType = "int", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", paramType = "query", dataType = "int", value = "分页大小", required = true),
            @ApiImplicitParam(name = "pageSize", paramType = "body", dataType = "UserDto", value = "查询条件", required = true)})
    @RequestMapping(value = "/selectUsers", method = RequestMethod.POST)
    public ICommonResponse selectUsers(@RequestParam(value = "pageNum") int pageNum,
                                       @RequestParam(value = "pageSize") int pageSize, @RequestBody UserDto userDto) {

        try {
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum,pageSize);
            PageResultDto pageResultDto = userService.selectPageUsers(userDto,pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,pageResultDto);
        } catch (BusinessRuntimeException e) {
            return new CommonResponse(e.getErrorCode());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new CommonResponse(CommonRespCodeEnum.FAIL_CODE);
        }
    }
}
