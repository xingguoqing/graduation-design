package com.xgq.dao;

import com.xgq.dto.UserDto;
import com.xgq.mapper.IUserMapper;
import com.xgq.po.UserPo;
import dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:29
 */
@Repository
public class UserDao {

    @Autowired
    IUserMapper userMapper;

    public List<UserPo> selectPageList(UserPo userPo, PageDto pageDto) {
        return userMapper.selectPageList(userPo, pageDto);
    }

    public int selectCount(UserPo userPo) {
        return userMapper.selectCount(userPo);
    }

    public void updateStatusByUserIds(List<Long> ids, String stauts) {
        userMapper.updateStatusByUserIds(ids, stauts);
    }

    public UserPo getUserByUserId(Long id) {
        return userMapper.getUserByUserId(id);
    }

    public void addUser(UserPo userPo) {
        userMapper.addUser(userPo);
    }

    public UserPo getUserByUserPhone(String userPhone) {
        return userMapper.getUserByUserPhone(userPhone);
    }

    public void updatPhoneById(String phone, Long id) {
        userMapper.updatPhoneById(phone, id);
    }

    public void updatePassword(String password, Long id) {
        userMapper.updatePassword(password, id);
    }

    public int selCountByUserIds(List<Long> userIds, UserDto userDto) {
        return userMapper.selCountByUserIds(userIds, userDto);
    }

    public List<UserPo> selPageUserByUserIds(List<Long> userIds, UserDto userDto, PageDto pageDto) {
        return userMapper.selPageUserByUserIds(userIds, userDto, pageDto);
    }

    public UserPo selUserById(Long id) {
        return userMapper.selUserById(id);
    }
}
