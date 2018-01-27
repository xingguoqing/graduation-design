package response;


import util.exception.BusinessRuntimeException;

/**
 * 响应状态码枚举类
 */
public enum ResponseCodeEnum {

    SUCCESS_CODE("0000", "操作成功"), DATA_INVALID("0001", "数据不合法");

    private String code;

    private String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCodeEnum getResponseCodeEnumByCode(String code) {
        ResponseCodeEnum[] responseCodeEnums = ResponseCodeEnum.values();
        for (ResponseCodeEnum responseCodeEnum : responseCodeEnums) {
            if (responseCodeEnum.getCode().equals(code)) {
                return responseCodeEnum;
            }
        }
        throw new BusinessRuntimeException("根据状态码{" + code + "}未查询到对应响应枚举值");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
