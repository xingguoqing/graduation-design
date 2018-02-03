package com.xgq.service;


import com.xgq.dto.UserDto;
import com.xgq.po.UserPo;
import dto.PageDto;
import dto.PageResultDto;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:31
 */
public interface IUserService {

    PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto);
}
