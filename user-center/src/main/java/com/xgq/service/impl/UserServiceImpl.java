package com.xgq.service.impl;

import com.xgq.dao.UserDao;
import com.xgq.po.UserPo;
import com.xgq.service.IUserService;
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

//    @Override
//    public List<UserPo> selectPageList(PageUtil pageUtil) {

//        String start =
//        return userDao.selectPageList(pageUtil.getO(),pageUtil.get,end);
//        return null;
//    }

    @Override
    public int selectCount(UserPo userPo) {
        return userDao.selectCount(userPo);
    }
}
