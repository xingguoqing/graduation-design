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

    public List<UserPo> selectPageList(UserPo userPo, PageDto pageDto){
        return userMapper.selectPageList(userPo,pageDto);
    }

    public int selectCount(UserPo userPo){
        return userMapper.selectCount(userPo);
    }

    public void updateStatusByUserCode(List<String> userCodes, String stauts) {
        userMapper.updateStatusByUserCode(userCodes,stauts);
    }

    public UserPo getUserByUserCode(String userCode) {
        return userMapper.getUserByUserCode(userCode);
    }

    public void addUser(UserPo userPo) {
        userMapper.addUser(userPo);
    }

    public UserPo getUserByUserPhone(String userPhone) {
        return userMapper.getUserByUserPhone(userPhone);
    }

    public void updatPhoneByCode(String phone, String userCode) {
        userMapper.updatPhoneByCode(phone,userCode);
    }

    public void updatPersonalProfileByCode(String personalProfile, String userCode) {
        userMapper.updatPersonalProfileByCode(personalProfile,userCode);
    }

    public void updateUserMail(String mail, String userCode) {
        userMapper.updateUserMail(mail,userCode);
    }

    public void updatePassword(String password, String userCode) {
        userMapper.updatePassword(password,userCode);
    }
}
