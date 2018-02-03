package com.xgq.dao;

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
}
