package com.xgq.controller;

import com.xgq.dto.JobDto;
import com.xgq.po.UserRolePo;
import com.xgq.service.IJobService;
import com.xgq.service.IUserRoleService;
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
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午11:59
 */
@Api(value = "报修任务相关接口", tags = "JobController")
@RestController
@RequestMapping("/public/job")
public class JobController {


    @Autowired
    IJobService jobService;


    private static final Logger LOGGER = LoggerFactory.getLogger(AddrController.class);

    @ApiOperation(value = "分页查询任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selPageJob", method = RequestMethod.POST)
    public ICommonResponse selPageJob(HttpServletRequest request, @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestBody JobDto jobDto) {
        try {
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
            jobDto.setSubUserId(userId);
            PageResultDto pageResultDto = jobService.selPageAddrs(jobDto, pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

}
