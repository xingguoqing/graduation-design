package responsecode.enums;

import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午9:16
 */
public enum StatusCodeEnum implements ICommonCode {

    STATUS_ILLEGAL("3001", "状态不合法");

    private String code;

    private String msg;

    StatusCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
