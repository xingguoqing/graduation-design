package com.xgq.mapper;

import com.xgq.dto.JobDto;
import com.xgq.dto.JobTypeDto;
import dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:27
 */
@Mapper
public interface IJobMapper {

    int selectCount(@Param("jobDto") JobDto jobDto);

    List<JobDto> selectPageList(@Param("jobDto") JobDto jobDto, @Param("page") PageDto pageDto);

    String selJobNameById(@Param("id") Long id);

    JobDto selJobById(@Param("id") Long id);

    void updateRepaireIdByJobId(@Param("jobId") Long jobId, @Param("repaireId") Long repaireId);

    List<JobDto> selSubJob(@Param("subId") Long id);

    void updateDesc(@Param("id") Long id, @Param("desc") String desc);

    void addEval(@Param("id") Long id, @Param("eval") String eval);

    List<JobTypeDto> selJobType();

    void addJob(@Param("jobDto") JobDto jobDto);

    void addReply(@Param("id") Long jobId, @Param("eval") String eval);

    void over(@Param("id") Long id);
}
