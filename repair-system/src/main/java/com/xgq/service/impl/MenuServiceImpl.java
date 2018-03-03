package com.xgq.service.impl;

import com.xgq.dao.MenuDao;
import com.xgq.dto.MenuDto;
import com.xgq.po.MenuPo;
import com.xgq.po.MenuRolePo;
import com.xgq.po.UserRolePo;
import com.xgq.service.IMenuRoleService;
import com.xgq.service.IMenuService;
import com.xgq.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:20
 */
@Service
public class MenuServiceImpl implements IMenuService {


    @Autowired
    MenuDao menuDao;

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    IMenuRoleService menuRoleService;

    @Override
    public List<MenuDto> selUserMenus(Long userId) {
        List<UserRolePo> userRolePos = userRoleService.selByUserId(userId);
        List<Long> roleIds = new ArrayList<Long>();
        for (UserRolePo userRolePo : userRolePos) {
            roleIds.add(userRolePo.getRoleId());
        }
        List<MenuRolePo> menuRolePos = menuRoleService.selByRoleIds(roleIds);

        List<Long> menuIds = new ArrayList<Long>();
        for (MenuRolePo menuRolePo : menuRolePos) {
            menuIds.add(menuRolePo.getMenuId());
        }
        List<MenuPo> menuPos = menuDao.selByMenuIds(menuIds);
        return parseToMenuDto(menuPos);
    }

    private List<MenuDto> parseToMenuDto(List<MenuPo> menuPos) {
        List<MenuDto> menuDtos = new ArrayList();
        MenuDto menuDto;
        for (MenuPo menuPo : menuPos) {
            menuDto = new MenuDto();
            menuDto.setId(menuPo.getId());
            menuDto.setName(menuPo.getMenuName());
            menuDto.setpId(menuPo.getParentId());
            menuDto.setUrl(menuPo.getMenuUrl());
            menuDto.setIsleaf(menuPo.getIsLeaf());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }
}
