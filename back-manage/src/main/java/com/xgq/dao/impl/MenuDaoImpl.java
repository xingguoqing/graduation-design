package com.xgq.dao.impl;

import com.xgq.dao.IMenuDao;
import com.xgq.dto.MenuDto;
import com.xgq.mapper.IMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/12 下午9:38
 */
@Repository
public class MenuDaoImpl implements IMenuDao {



    @Autowired
    private IMenuMapper menuMapper;

    @Override
    public List<MenuDto> selAllMenu() {
        return menuMapper.selAllMenu();
    }
}
