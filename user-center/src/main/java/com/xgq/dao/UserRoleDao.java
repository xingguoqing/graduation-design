package com.xgq.dao;

import com.xgq.mapper.IUserMapper;
import com.xgq.mapper.IUserRoleMapper;
import com.xgq.po.UserRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:19
 */
@Repository
public class UserRoleDao {

    @Autowired
    private IUserRoleMapper userRoleMapper;

    public Long addUserRole(UserRolePo userRolePo) {
        return userRoleMapper.addUserRole(userRolePo);
    }

    public List<UserRolePo> selByUserId(Long userId) {
        return userRoleMapper.selByUserId(userId);
    }

    public List<UserRolePo> selUserRoleByRoleId(Long id) {
        return userRoleMapper.selUserRoleByRoleId(id);
    }
}
