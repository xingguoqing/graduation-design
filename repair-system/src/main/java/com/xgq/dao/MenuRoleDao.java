package com.xgq.dao;

import com.xgq.mapper.IMenuRoleMapper;
import com.xgq.po.MenuRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:49
 */
@Repository
public class MenuRoleDao {

    @Autowired
    IMenuRoleMapper menuRoleMapper;

    public List<MenuRolePo> selByRoleIds(List<Long> roleIds) {
        return menuRoleMapper.selByRoleIds(roleIds);
    }
}
