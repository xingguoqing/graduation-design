package com.xgq.service.impl;

import com.xgq.dao.UserDao;
import com.xgq.dto.UserDto;
import com.xgq.po.UserPo;
import com.xgq.service.IUserService;
import dto.PageDto;
import dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:32
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto) {

        UserPo userPo = parseToUserPo(userDto);
        int count = userDao.selectCount(userPo);
        List<UserPo> users = userDao.selectPageList(userPo, pageDto);
        PageResultDto pageResultDto = new PageResultDto();
        pageResultDto.setCount(count);
        pageResultDto.setDatas(users);
        return pageResultDto;
    }

    private UserPo parseToUserPo(UserDto userDto) {
        UserPo userPo = new UserPo();
        userPo.setUserPhone(userDto.getUserPhone());
        userPo.setPersonalProfile(userDto.getPersonalProfile());
        userPo.setStatus(userDto.isStatus());
        userPo.setUserCode(userDto.getUserCode());
        userPo.setUserMail(userDto.getUserMail());
        userPo.setUserName(userDto.getUserName());
        return userPo;
    }
}
