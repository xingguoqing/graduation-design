package com.xgq.mapper;


import com.xgq.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/17 下午4:13
 */
@Repository
public interface IUserMapper {


    List<UserPo> selectPageList(@Param("user") UserPo userPo,@Param("start") int start, @Param("end") int end);

    List<UserPo> selectList(@Param("user") UserPo userPo);



    int selectCount(@Param("user") UserPo userPo);

}
