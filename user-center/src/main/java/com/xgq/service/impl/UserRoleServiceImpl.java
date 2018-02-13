package com.xgq.service.impl;

import com.xgq.dao.UserRoleDao;
import com.xgq.po.UserRolePo;
import com.xgq.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
