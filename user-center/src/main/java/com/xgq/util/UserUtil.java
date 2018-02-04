package com.xgq.util;

import com.xgq.dto.UserDto;
import com.xgq.errorcode.UserErrorCode;
import com.xgq.po.UserPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import util.exception.BusinessRuntimeException;

import java.util.regex.Pattern;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午10:37
 */
public class UserUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);

    /**
     * 验证手机号码
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     */
    private static final String REGEX_PHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

    private static final String REGEX_MAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static void vailUserParams(UserPo userPo) {

        LOGGER.info("【用户中心】开始校验用户参数");
        if (StringUtils.isEmpty(userPo.getUserCode())) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.CODE_ILLEGAL);
        }
        if (StringUtils.isEmpty(userPo.getUserName())) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.NAME_ILLEGAL);
        }
        if (StringUtils.isEmpty(userPo.getUserPassword())) {
            BusinessRuntimeException.wrapBusiException(UserErrorCode.PASSWORD_ILLEGAL);
        }
        vailUserPhone(userPo.getUserPhone());
        vailUserMail(userPo.getUserMail());
        LOGGER.info("【用户中心】用户参数校验结束:{}", userPo);
    }

    public static void vailUserPhone(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            if (!Pattern.matches(REGEX_PHONE, phone)) {
                BusinessRuntimeException.wrapBusiException(UserErrorCode.PHONE_ILLEGAL);
            }
        }
    }

    public static void vailUserMail(String mail) {
        if (!StringUtils.isEmpty(mail)) {
            if (!Pattern.matches(REGEX_MAIL, mail)) {
                BusinessRuntimeException.wrapBusiException(UserErrorCode.MAIL_ILLEGAL);
            }
        }
    }

}
