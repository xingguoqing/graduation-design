package com.xgq.service.impl;

import com.xgq.dao.JobDao;
import com.xgq.dto.JobDto;
import com.xgq.dto.JobTypeDto;
import com.xgq.enums.JobStatusEnum;
import com.xgq.enums.JobTypeEnum;
import com.xgq.enums.RoleEnum;
import com.xgq.errorcode.JobStatusErrorEnum;
import com.xgq.po.UserRolePo;
import com.xgq.service.IJobService;
import com.xgq.service.IUserRoleService;
import dto.PageDto;
import dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.exception.BusinessRuntimeException;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:12
 */
@Service
public class JobServiceImpl implements IJobService {


    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    JobDao jobDao;


    @Override
    public PageResultDto selPageAddrs(JobDto jobDto, PageDto pageDto) {

        List<UserRolePo> userRolePoList = userRoleService.selByUserId(jobDto.getSubUserId());
        for (UserRolePo po : userRolePoList){
            //拥有管理员角色，查询所有任务
            if(RoleEnum.ADMINISTRATORS.getCode().equals(po.getRoleId())){
                jobDto.setSubUserId(null);
            }
        }
        int count = jobDao.selectCount(jobDto);
        List<JobDto> jobDtoList = jobDao.selectPageList(jobDto, pageDto);
        for(JobDto jobDto1:jobDtoList){
            jobDto1.setJobType(JobTypeEnum.getNameByCode(jobDto1.getJobType()));
            jobDto1.setStatus(JobStatusEnum.getNameByCode(jobDto1.getStatus()));
        }
        PageResultDto pageResultDto = new PageResultDto();
        pageResultDto.setTotal(count);
        pageResultDto.setRows(jobDtoList);
        return pageResultDto;
    }

    @Override
    public String selJobNameById(Long id) {
        return jobDao.selJobNameById(id);
    }

    @Override
    public JobDto selJobById(Long id) {
        return jobDao.selJobById(id);
    }

    @Override
    public void distribute(Long jobId,Long repaireId) {
        jobDao.updateRepaireIdByJobId(jobId,repaireId);
    }

    @Override
    public List<JobDto> selSubJob(Long id) {
        return jobDao.selSubJob(id);
    }

    @Override
    public void updateDesc(Long id, String desc) {
        jobDao.updateDesc(id,desc);
    }

    @Override
    public void addEval(Long jobId, String eval) {
        JobDto jobDto = jobDao.selJobById(jobId);
        if(JobStatusEnum.OVER.getCode().equals(jobDto.getStatus())){
            jobDao.addEval(jobId,eval);
        }else {
            BusinessRuntimeException.wrapBusiException(JobStatusErrorEnum.ENABLE_ADD_EVAL);
        }
    }

    @Override
    public List<JobTypeDto> selJobType() {
        return jobDao.selJobType();
    }

    @Override
    public void addJob(JobDto jobDto) {

        jobDao.addJob(jobDto);
    }

    @Override
    public void addReply(Long jobId, String eval) {
        jobDao.addReply(jobId,eval);
    }

    @Override
    public void over(Long id) {
        jobDao.over(id);
    }
}
