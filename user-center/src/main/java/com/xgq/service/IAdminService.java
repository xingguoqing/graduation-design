package com.xgq.service;

import com.xgq.dto.UserDto;
import dto.PageDto;
import dto.PageResultDto;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午1:59
 */
public interface IAdminService {
    PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto);
}
