package com.xgq.service.impl;

import com.xgq.dao.UserRoleDao;
import com.xgq.po.UserRolePo;
import com.xgq.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:19
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Long addUserRole(UserRolePo userRolePo) {
        return userRoleDao.addUserRole(userRolePo);
    }

    @Override
    public List<UserRolePo> selByUserId(Long userId) {
        return userRoleDao.selByUserId(userId);
    }

    @Override
    public List<UserRolePo> selUserRoleByRoleId(Long id) {
        return userRoleDao.selUserRoleByRoleId(id);
    }
}
