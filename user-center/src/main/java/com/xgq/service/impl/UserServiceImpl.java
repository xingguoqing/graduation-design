package com.xgq.service.impl;

import com.xgq.dao.UserDao;
import com.xgq.dto.UserDto;
import com.xgq.enums.RoleEnum;
import com.xgq.errorcode.RoleErrorCode;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.po.UserPo;
import com.xgq.po.UserRolePo;
import com.xgq.service.IUserRoleService;
import com.xgq.service.IUserService;
import dto.PageDto;
import dto.PageResultDto;
import enums.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.exception.BusinessRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/1/29 下午9:32
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;



    /**
     * 分页查询普通用户
     *
     * @param type 用户类型
     * @param userDto 用户查询条件实体
     * @param pageDto 分页类（不分页的话传null就可以）
     * @return
     */
    @Override
    public PageResultDto selectPageUsers(Long type,UserDto userDto, PageDto pageDto) {
        List<UserRolePo> userRolePos = new ArrayList<UserRolePo>();
        if(RoleEnum.ORDINARY_USER.getCode().equals(type)){
            userRolePos = userRoleService.selUserRoleByRoleId(RoleEnum.ORDINARY_USER.getCode());
        }else if(RoleEnum.REPAIR_PERSONNEL.getCode().equals(type)){
            userRolePos = userRoleService.selUserRoleByRoleId(RoleEnum.REPAIR_PERSONNEL.getCode());
        }else {
            BusinessRuntimeException.wrapBusiException(RoleErrorCode.ROLE_ILLEGAL);
        }
        List<Long> userIds = new ArrayList<Long>();
        for (UserRolePo userRolePo : userRolePos) {
            userIds.add(userRolePo.getUserId());
        }
        List<UserPo> userPos = selPageUserByUserIds(userIds,userDto,pageDto);
        int count = selCountByUserIds(userIds,userDto);
        PageResultDto  pageResultDto = new PageResultDto();
        pageResultDto.setRows(userPos);
        pageResultDto.setTotal(count);
        return pageResultDto;
    }

    private int selCountByUserIds(List<Long> userIds,UserDto userDto) {
        return userDao.selCountByUserIds(userIds,userDto);
    }

    public List<UserPo> selPageUserByUserIds(List<Long> userIds,UserDto userDto,PageDto pageDto){
        return userDao.selPageUserByUserIds(userIds,userDto,pageDto);
    }

    @Override
    public void updateStatusByUserIds(List<Long> ids, String stauts) {
        userDao.updateStatusByUserIds(ids, stauts);
    }

    @Override
    public UserDto getUserByUserId(Long id) {
        UserPo userPo = userDao.getUserByUserId(id);
        return parseToUserDto(userPo);
    }

    @Override
    public void addUser(UserPo userPo,String type) {
        UserDto userDto = getUserByUserPhone(userPo.getUserPhone());
        if (userDto != null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.PHONE_TAKE_UP);
        }
        //默认禁用状态
        userPo.setUserStatus(StatusEnum.ENABLE.getCode());
        userDao.addUser(userPo);
//        //添加用户角色
        UserRolePo userRolePo = new UserRolePo();
        if(type == "1")
            userRolePo.setRoleId(RoleEnum.ORDINARY_USER.getCode());
        else
            userRolePo.setRoleId(RoleEnum.REPAIR_PERSONNEL.getCode());
        userRolePo.setUserId(userPo.getUserId());
        userRoleService.addUserRole(userRolePo);
    }

    @Override
    public UserDto getUserByUserPhone(String userPhone) {
        UserPo userPo = userDao.getUserByUserPhone(userPhone);
        return parseToUserDto(userPo);
    }


    @Override
    public void updatePassword(String password, Long id) {
        UserDto userDto = getUserByUserId(id);
        if (userDto == null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.USER_NOT_EXIST);
        }
        userDao.updatePassword(password, id);
    }

    @Override
    public UserDto login(String userPhone, String password) {
        UserPo userPo = userDao.getUserByUserPhone(userPhone);
        if (userPo == null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.USER_NOT_EXIST);
        }
        if (StatusEnum.ENABLE.getCode().equals(userPo.getUserStatus())) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.USER_NOT_ACTIVATION);
        }
        if (userPo.getUserPassword().equals(password)) {
            return parseToUserDto(userPo);
        } else {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.LOGIN_ILLEGAL);
        }
        return null;
    }

    @Override
    public void updateUserPhone(String phone, Long id) {
        UserDto userDto = getUserByUserId(id);
        if (userDto == null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.USER_NOT_EXIST);
        }
        userDao.updatPhoneById(phone, id);
    }

//    @Override
//    public void addRepaire(UserPo userPo) {
//        UserDto userDto = getUserByUserPhone(userPo.getUserPhone());
//        if (userDto != null) {
//            BusinessRuntimeException.wrapBusiException(UserErrorCode.PHONE_TAKE_UP);
//        }
//        //默认禁用状态
//        userPo.setUserStatus(StatusEnum.ENABLE.getCode());
//        userDao.addUser(userPo);
//        //添加用户角色
//        UserRolePo userRolePo = new UserRolePo();
//        userRolePo.setRoleId(RoleEnum.REPAIR_PERSONNEL.getCode());
//        userRolePo.setUserId(userPo.getUserId());
//        userRoleService.addUserRole(userRolePo);
//    }

    @Override
    public UserPo selUserById(Long id) {
        return userDao.selUserById(id);
    }

    private UserDto parseToUserDto(UserPo userPo) {
        if (userPo == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(userPo.getUserId());
        userDto.setUserStatus(userPo.getUserStatus());
        userDto.setUserName(userPo.getUserName());
        userDto.setUserPhone(userPo.getUserPhone());
        return userDto;
    }

    private UserPo parseToUserPo(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserPo userPo = new UserPo();
        userPo.setUserPhone(userDto.getUserPhone());
        userPo.setUserStatus(userDto.getUserStatus());
        userPo.setUserName(userDto.getUserName());
        return userPo;
    }
}
