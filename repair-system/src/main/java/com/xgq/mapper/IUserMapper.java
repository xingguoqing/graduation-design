package com.xgq.mapper;


import com.xgq.dto.UserDto;
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

    void updateStatusByUserIds(@Param("userIds") List<Long> userIds, @Param("stauts") String stauts);

    UserPo getUserByUserId(@Param("id") Long id);

    void addUser(@Param("user") UserPo userPo);

    UserPo getUserByUserPhone(@Param("userPhone") String userPhone);

    void updatPhoneById(@Param("phone") String phone, @Param("id") Long id);

    void updatePassword(@Param("password") String password, @Param("id") Long id);



    int selCountByUserIds(@Param("userIds") List<Long> userIds, @Param("user") UserDto userDto);

    List<UserPo> selPageUserByUserIds(@Param("userIds") List<Long> userIds, @Param("user") UserDto userDto, @Param("page") PageDto pageDto);

    UserPo selUserById(@Param("id") Long id);
}
