package com.xgq.dao;

import com.xgq.mapper.IUserMapper;
import com.xgq.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:29
 */
@Component
public class UserDao {

    @Autowired
    IUserMapper userMapper;

    public List<UserPo> selectPageList(UserPo userPo,int start,int end){
        return userMapper.selectPageList(userPo,start,end);
    }

    public int selectCount(UserPo userPo){
        return userMapper.selectCount(userPo);
    }
}
