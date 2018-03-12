package com.xgq.service.impl;

import com.xgq.dao.IMenuDao;
import com.xgq.dto.MenuDto;
import com.xgq.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:20
 */
@Service
public class MenuServiceImpl implements IMenuService {


    @Autowired
    IMenuDao menuDao;

    @Override
    public List<MenuDto> selAllMenu() {
        return menuDao.selAllMenu();
    }
}
