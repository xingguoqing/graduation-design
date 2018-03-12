package com.xgq.mapper;

import com.xgq.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/3/12 下午9:39
 */
@Mapper
public interface IMenuMapper {

    List<MenuDto> selAllMenu();
}
