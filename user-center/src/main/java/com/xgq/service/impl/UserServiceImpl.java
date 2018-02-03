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

    @Override
    public void changeUserStatus(List<String> userCodes, String stauts) {
        userDao.updateStatusByUserCode(userCodes, stauts);
    }

    @Override
    public UserDto getUserByUserCode(String userCode) {
        UserPo userPo = userDao.getUserByUserCode(userCode);
        return parseToUserDto(userPo);
    }

    private UserPo parseToUserPo(UserDto userDto) {
        UserPo userPo = new UserPo();
        userPo.setUserPhone(userDto.getUserPhone());
        userPo.setPersonalProfile(userDto.getPersonalProfile());
        userPo.setStatus(userDto.getStatus());
        userPo.setUserCode(userDto.getUserCode());
        userPo.setUserMail(userDto.getUserMail());
        userPo.setUserName(userDto.getUserName());
        return userPo;
    }

    private UserDto parseToUserDto(UserPo userPo) {
        UserDto userDto = new UserDto();
        userDto.setPersonalProfile(userPo.getPersonalProfile());
        userDto.setStatus(userPo.getStatus());
        userDto.setUserCode(userPo.getUserCode());
        userDto.setUserMail(userPo.getUserMail());
        userDto.setUserName(userPo.getUserName());
        userDto.setUserPhone(userPo.getUserPhone());
        return userDto;
    }
}
