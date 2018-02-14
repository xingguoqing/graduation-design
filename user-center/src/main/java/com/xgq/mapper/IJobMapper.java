package com.xgq.mapper;

import com.xgq.dto.JobDto;
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

    List<JobDto> selectPageList(@Param("jobDto")JobDto jobDto,@Param("page") PageDto pageDto);
}
