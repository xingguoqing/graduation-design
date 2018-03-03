package com.xgq.service.impl;

import com.xgq.dao.MenuRoleDao;
import com.xgq.po.MenuRolePo;
import com.xgq.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:48
 */
@Service
public class MenuRoleServiceImpl implements IMenuRoleService{

    @Autowired
    MenuRoleDao menuRoleDao;

    @Override
    public List<MenuRolePo> selByRoleIds(List<Long> roleIds) {
        return menuRoleDao.selByRoleIds(roleIds);
    }
}
