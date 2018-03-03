package com.xgq.mapper;

import com.xgq.po.PartDirectoryPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午10:33
 */
@Mapper
public interface IPartDirectoryMapper {

    PartDirectoryPo getNameById(@Param("id") Long id);
}
