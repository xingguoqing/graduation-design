package com.xgq.service;

import com.xgq.po.UserRolePo;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:15
 */
public interface IUserRoleService {

    Long addUserRole(UserRolePo userRolePo);

    List<UserRolePo> selByUserId(Long userId);

    List<UserRolePo> selUserRoleByRoleId(Long code);
}
