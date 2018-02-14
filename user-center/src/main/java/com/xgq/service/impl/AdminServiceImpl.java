package com.xgq.service.impl;

import com.xgq.dto.UserDto;
import com.xgq.po.UserPo;
import com.xgq.service.IAdminService;
import dto.PageDto;
import dto.PageResultDto;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午2:00
 */
public class AdminServiceImpl {

//    @Override
//    public PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto) {
//
//        UserPo userPo = parseToUserPo(userDto);
//        int count = userDao.selectCount(userPo);
//        List<UserPo> users = userDao.selectPageList(userPo, pageDto);
//        PageResultDto pageResultDto = new PageResultDto();
//        pageResultDto.setCount(count);
//        pageResultDto.setDatas(users);
//        return pageResultDto;
//    }
}
