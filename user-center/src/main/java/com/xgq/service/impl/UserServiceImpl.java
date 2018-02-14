package com.xgq.service.impl;

import com.xgq.dao.UserDao;
import com.xgq.dto.UserDto;
import com.xgq.enums.RoleEnum;
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
import org.springframework.transaction.annotation.Transactional;
import util.exception.BusinessRuntimeException;

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

    @Override
    public PageResultDto selectPageUsers(UserDto userDto, PageDto pageDto) {

        UserPo userPo = parseToUserPo(userDto);
        int count = userDao.selectCount(userPo);
        List<UserPo> users = userDao.selectPageList(userPo, pageDto);
        PageResultDto pageResultDto = new PageResultDto();
        pageResultDto.setCount(count);
        pageResultDto.setDatas(users);
        return pageResultDto;
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
    @Transactional
    public void addUser(UserPo userPo) {
        UserDto userDto = getUserByUserPhone(userPo.getUserPhone());
        if (userDto != null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.PHONE_TAKE_UP);
        }
        //默认禁用状态
        userPo.setUserStatus(StatusEnum.ENABLE.getCode());
        userDao.addUser(userPo);
        //添加用户角色
        UserRolePo userRolePo = new UserRolePo();
        userRolePo.setRoleId(RoleEnum.ORDINARY_USER.getCode());
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

    @Override
    public void addRepaire(UserPo userPo) {
        UserDto userDto = getUserByUserPhone(userPo.getUserPhone());
        if (userDto != null) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.PHONE_TAKE_UP);
        }
        //默认禁用状态
        userPo.setUserStatus(StatusEnum.ENABLE.getCode());
        userDao.addUser(userPo);
        //添加用户角色
        UserRolePo userRolePo = new UserRolePo();
        userRolePo.setRoleId(RoleEnum.REPAIR_PERSONNEL.getCode());
        userRolePo.setUserId(userPo.getUserId());
        userRoleService.addUserRole(userRolePo);
    }

    private UserDto parseToUserDto(UserPo userPo) {
        if (userPo == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userPo.getUserId());
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
