package response;


import lombok.Getter;

/**
 * 响应状态码枚举类
 */
@Getter
public enum ResponseCodeEnum {

    SUCCESS_CODE("0000", "操作成功"), DATA_INVALID("0001", "数据不合法");

    private String code;

    private String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
