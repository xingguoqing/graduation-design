package com.xgq.mapper;

import com.xgq.po.UserRolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:20
 */
@Mapper
public interface IUserRoleMapper {

    Long addUserRole(@Param("userRole") UserRolePo userRolePo);
}
