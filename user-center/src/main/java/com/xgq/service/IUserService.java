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
     * @param userCodes 用户编码
     * @param stauts 更改后的状态
     */
    void changeUserStatus(List<String> userCodes, String stauts);


    /**
     * 根据用户编号查询用户
     * @param userCode 用户编号
     * @return 查询结果
     */
    UserDto getUserByUserCode(String userCode);

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

    void updatPhoneByCode(String phone, String userCode);

    void updatPersonalProfileByCode(String personalProfile, String userCode);

    void updateUserMail(String mail, String userCode);

    void updatePassword(String password, String userCode);
}
