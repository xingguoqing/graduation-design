package com.xgq.service;

import com.xgq.po.MenuRolePo;

import java.util.List; /**
 * @author xingguoqing
 * @date 2018/2/15 上午10:40
 */
public interface IMenuRoleService {

    List<MenuRolePo> selByRoleIds(List<Long> roleIds);
}
