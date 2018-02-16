package com.xgq.dao;

import com.xgq.mapper.IMenuMapper;
import com.xgq.po.MenuPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:29
 */
@Repository
public class MenuDao {


    @Autowired
    IMenuMapper menuMapper;

    public List<MenuPo> selByMenuIds(List<Long> menuIds) {
        return menuMapper.selByMenuIds(menuIds);
    }
}
