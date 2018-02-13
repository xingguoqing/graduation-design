package com.xgq.service;


import com.xgq.dto.UserDto;
import com.xgq.po.UserPo;
import dto.PageDto;
import dto.PageResultDto;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:31
 */
public interface IUserService {

    /**
     * 分页查询用户
     * @param userDto 用户查询条件实体
     * @param pageDto 分页类（不分页的话传null就可以）
     * @return 分页结果
     */
    PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto);

    /**
     * 更改用户状态
     * @param ids 用户编码
     * @param stauts 更改后的状态
     */
    void updateStatusByUserIds(List<Long> ids, String stauts);

    /**
     * 根据用户编号查询用户
     * @param id 用户编号
     * @return 查询结果
     */
    UserDto getUserByUserId(Long id);

    /**
     * 添加用户
     * @param userDto
     */
    void addUser(UserPo userDto);

    /**
     * 根据用户手机号查询
     * @param userPhone 手机号
     * @return
     */
    UserDto getUserByUserPhone(String userPhone);

//    /**
//     * 更新手机号
//     * @param phone 手机号
//     * @param id 用户编号
//     */
//    void updatPhoneById(String phone, Long id);

    /**
     * 修改密码
     * @param password 密码
     * @param id 用户编号
     */
    void updatePassword(String password, Long id);

    /**
     * 用户登录
     * @param userPhone
     * @param password
     * @return
     */
    UserDto login(String userPhone, String password);


    void updateUserPhone(String phone, Long id);
}
