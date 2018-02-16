package com.xgq.service.impl;

import com.xgq.dao.JobDao;
import com.xgq.dto.JobDto;
import com.xgq.enums.RoleEnum;
import com.xgq.po.UserPo;
import com.xgq.po.UserRolePo;
import com.xgq.service.IJobService;
import com.xgq.service.IUserRoleService;
import dto.PageDto;
import dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageResultDto pageResultDto = new PageResultDto();
        pageResultDto.setTotal(count);
        pageResultDto.setRows(jobDtoList);
        return pageResultDto;
    }
}
