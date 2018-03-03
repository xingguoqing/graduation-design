package com.xgq.service;

import com.xgq.dto.MenuDto;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:20
 */
public interface IMenuService {

    List<MenuDto> selUserMenus(Long userId);
}
