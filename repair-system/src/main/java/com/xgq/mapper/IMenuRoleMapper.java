package com.xgq.mapper;

import com.xgq.po.MenuRolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:50
 */
@Mapper
public interface IMenuRoleMapper {

    List<MenuRolePo> selByRoleIds(@Param("roleIds") List<Long> roleIds);
}
