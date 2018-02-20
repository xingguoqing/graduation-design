package com.xgq.dao;

import com.xgq.dto.JobDto;
import com.xgq.dto.JobTypeDto;
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

    public String selJobNameById(Long id) {
        return jobMapper.selJobNameById(id);
    }

    public JobDto selJobById(Long id) {
        return jobMapper.selJobById(id);
    }

    public void updateRepaireIdByJobId(Long id,Long repaireId) {
        jobMapper.updateRepaireIdByJobId(id,repaireId);
    }

    public List<JobDto> selSubJob(Long id) {
        return jobMapper.selSubJob(id);
    }

    public void updateDesc(Long id, String desc) {
        jobMapper.updateDesc(id,desc);
    }

    public void addEval(Long id, String eval) {
        jobMapper.addEval(id,eval);
    }

    public List<JobTypeDto> selJobType() {
        return jobMapper.selJobType();
    }

    public void addJob(JobDto jobDto) {
        jobMapper.addJob(jobDto);
    }

    public void addReply(Long jobId, String eval) {
        jobMapper.addReply(jobId,eval);
    }

    public void over(Long id) {
        jobMapper.over(id);
    }
}
