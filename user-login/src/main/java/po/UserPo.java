package po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserPo {

    private String userId;

    private String userCode;

    private String userPassword;

    private String userMail;

    private String userPhone;

    private String userName;

    private String personalProfile;

    private String status;

    private String lastLogin;

    private String createTime;

    private String ts;

}
