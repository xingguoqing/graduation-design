package com.xgq.controller;

import com.xgq.dto.JobDto;
import com.xgq.dto.JobTypeDto;
import com.xgq.enums.JobStatusEnum;
import com.xgq.enums.RoleEnum;
import com.xgq.po.UserRolePo;
import com.xgq.service.IJobService;
import com.xgq.service.IUserRoleService;
import com.xgq.util.UserUtil;
import dto.PageDto;
import dto.PageResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jwt.JwtUtils;
import org.apache.ibatis.annotations.Param;
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
    private IJobService jobService;

    @Autowired
    private IUserRoleService userRoleService;


    private static final Logger LOGGER = LoggerFactory.getLogger(AddrController.class);

    @ApiOperation(value = "分页查询任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selPageJob", method = RequestMethod.POST)
    public ICommonResponse selPageJob(HttpServletRequest request, @RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
        try {
            JobDto jobDto = new JobDto();
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
            jobDto.setSubUserId(userId);
            List<UserRolePo> userRolePos = userRoleService.selByUserId(userId);
            for(UserRolePo userRolePo : userRolePos){
                if(RoleEnum.ADMINISTRATORS.getCode().equals(userRolePo.getRoleId())){
                    jobDto.setSubUserId(null);
                }
            }
            PageResultDto pageResultDto = jobService.selPageAddrs(jobDto, pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "分页接受任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/repaireSelAcceptJob", method = RequestMethod.POST)
    public ICommonResponse repaireSelAcceptJob(HttpServletRequest request, @RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
        try {
            JobDto jobDto = new JobDto();
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
            jobDto.setRepairerId(userId);
            List<UserRolePo> userRolePos = userRoleService.selByUserId(userId);
            for(UserRolePo userRolePo : userRolePos){
                if(RoleEnum.ADMINISTRATORS.getCode().equals(userRolePo.getRoleId())){
                    jobDto.setSubUserId(null);
                }
            }
            PageResultDto pageResultDto = jobService.selPageAddrs(jobDto, pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "分页提交的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/repaireSelSubJob", method = RequestMethod.POST)
    public ICommonResponse repaireSelSubJob(HttpServletRequest request, @RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
        try {
            JobDto jobDto = new JobDto();
            PageUtil.validParams(pageNum, pageSize);
            PageDto pageDto = PageUtil.getPageDto(pageNum, pageSize);
            Long userId = JwtUtils.parseJWT(request.getHeader("sign"));
            jobDto.setStatus(JobStatusEnum.SUB.getCode());
            List<UserRolePo> userRolePos = userRoleService.selByUserId(userId);
            for(UserRolePo userRolePo : userRolePos){
                if(RoleEnum.ADMINISTRATORS.getCode().equals(userRolePo.getRoleId())){
                    jobDto.setSubUserId(null);
                }
            }
            PageResultDto pageResultDto = jobService.selPageAddrs(jobDto, pageDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, pageResultDto);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "添加任务")
    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public ICommonResponse addJob(@RequestBody JobDto jobDto ) {
        try {
            jobDto.setSubUserId(UserUtil.getUserId());
            jobService.addJob(jobDto);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }



    @ApiOperation(value = "查询任务名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selJobName", method = RequestMethod.GET)
    public ICommonResponse selJobName(@Param("id") Long id) {
        try {
            String name = jobService.selJobNameById(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, name);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "查询任务描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selJobDesc", method = RequestMethod.GET)
    public ICommonResponse selJobDesc(@Param("id") Long id) {
        try {
            JobDto jobDto = jobService.selJobById(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, jobDto.getJobDesc());
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "查询任务描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Long", paramType = "query", value = "任务id", required = true),
            @ApiImplicitParam(name = "desc", dataType = "String", paramType = "query", value = "任务描述", required = true)
    })
    @RequestMapping(value = "/updateDesc", method = RequestMethod.GET)
    public ICommonResponse updateDesc(@Param("id") Long id,@Param("desc") String desc) {
        try {
            jobService.updateDesc(id,desc);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "查询修理员回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selRepaireReply", method = RequestMethod.GET)
    public ICommonResponse selRepaireReply(@Param("id") Long id) {
        try {
            JobDto jobDto = jobService.selJobById(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, jobDto.getRepairerReply());
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }


    @ApiOperation(value = "查询用户评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selEvaluate", method = RequestMethod.GET)
    public ICommonResponse selEvaluate(@Param("id") Long id) {
        try {
            JobDto jobDto = jobService.selJobById(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, jobDto.getEvaluate());
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "分配任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/distribute", method = RequestMethod.GET)
    public ICommonResponse distribute(@RequestParam("jobId") Long jobId,@RequestParam(value = "repaireId",required = false) Long repaireId) {
        try {
            if(repaireId == null){
                repaireId = UserUtil.getUserId();
            }
            jobService.distribute(jobId,repaireId);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "完成任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/over", method = RequestMethod.GET)
    public ICommonResponse over(@RequestParam("id") Long id) {
        try {
            jobService.over(id);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }

    @ApiOperation(value = "添加评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/addEval", method = RequestMethod.GET)
    public ICommonResponse addEval(@RequestParam("jobid") Long jobId,@RequestParam("eval") String eval) {
        try {
            jobService.addEval(jobId,eval);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }


    @ApiOperation(value = "添加回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/addReply", method = RequestMethod.GET)
    public ICommonResponse addReply(@RequestParam("jobid") Long jobId,@RequestParam("eval") String eval) {
        try {
            jobService.addReply(jobId,eval);
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "分页查询任务失败");
        }
    }


    @ApiOperation(value = "查询任务类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query", value = "分页页数", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query", value = "分页大小", required = true)
    })
    @RequestMapping(value = "/selJobType", method = RequestMethod.GET)
    public ICommonResponse selJobType() {
        try {
            List<JobTypeDto> jobTypeDtos = jobService.selJobType();
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,jobTypeDtos);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "查询任务类型失败");
        }
    }



}
