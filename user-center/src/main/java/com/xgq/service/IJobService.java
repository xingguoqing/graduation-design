package com.xgq.service;

import com.xgq.dto.JobDto;
import dto.PageDto;
import dto.PageResultDto;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:12
 */
public interface IJobService {

    PageResultDto selPageAddrs(JobDto jobDto, PageDto pageDto);
}
