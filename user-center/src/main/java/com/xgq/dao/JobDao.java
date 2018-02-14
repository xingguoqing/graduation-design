package com.xgq.dao;

import com.xgq.dto.JobDto;
import com.xgq.mapper.IJobMapper;
import dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:22
 */
@Repository
public class JobDao {

    @Autowired
    IJobMapper jobMapper;

    public int selectCount(JobDto jobDto) {
        return jobMapper.selectCount(jobDto);
    }

    public List<JobDto> selectPageList(JobDto jobDto, PageDto pageDto) {
        return jobMapper.selectPageList(jobDto,pageDto);
    }
}
