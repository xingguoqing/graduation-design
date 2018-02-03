package com.xgq.mapper;


import com.xgq.po.UserPo;
import dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/17 下午4:13
 */
@Mapper
public interface IUserMapper {


    List<UserPo> selectPageList(@Param("user") UserPo userPo, @Param("page") PageDto pageDto);


    int selectCount(@Param("user") UserPo userPo);

    void updateStatusByUserCode(@Param("userCodes") List<String> userCodes, @Param("stauts") String stauts);

    UserPo getUserByUserCode(@Param("userCode") String userCode);

    void addUser(@Param("user") UserPo userPo);

    UserPo getUserByUserPhone(@Param("userPhone") String userPhone);
}
