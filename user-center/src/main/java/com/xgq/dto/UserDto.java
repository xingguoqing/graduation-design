package com.xgq.dto;

import com.mysql.fabric.xmlrpc.base.Data;
import lombok.Getter;
import lombok.Setter;
//import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserDto {

    private String userCode;

    private String userMail;

    private String userPhone;

    private String userName;

    private String personalProfile;

    private boolean status;

}
