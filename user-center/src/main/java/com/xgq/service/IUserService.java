package com.xgq.service;


import com.xgq.po.UserPo;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:31
 */
public interface IUserService {


//    List<UserPo> selectPageList(PageUtil pageUtil);

    int selectCount(UserPo userPo);

}
