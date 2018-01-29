package com.xgq.po;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserPo {

    private Integer userId;

    private String userCode;

    private String userPassword;

    private String userMail;

    private String userPhone;

    private String userName;

    private String personalProfile;

    private boolean status;

    private TimeStamp lastLogin;

    private TimeStamp createTime;

    private TimeStamp ts;

}
