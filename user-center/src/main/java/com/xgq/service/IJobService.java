package com.xgq.service;

import com.xgq.dto.JobDto;
import com.xgq.dto.JobTypeDto;
import dto.PageDto;
import dto.PageResultDto;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:12
 */
public interface IJobService {

    PageResultDto selPageAddrs(JobDto jobDto, PageDto pageDto);

    String selJobNameById(Long id);

    JobDto selJobById(Long id);

    void distribute(Long jobId,Long repaireId);

    List<JobDto> selSubJob(Long id);

    void updateDesc(Long id, String desc);

    void addEval(Long jobId, String eval);

    List<JobTypeDto> selJobType();

    void addJob(JobDto jobDto);

    void addReply(Long jobId, String eval);

    void over(Long id);
}
