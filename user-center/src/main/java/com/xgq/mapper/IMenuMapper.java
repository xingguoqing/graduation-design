package com.xgq.mapper;

import com.xgq.po.MenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:33
 */
@Mapper
public interface IMenuMapper {

    List<MenuPo> selByMenuIds(@Param("menuIds") List<Long> menuIds);
}
