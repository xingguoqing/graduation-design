package responsecode.enums;


import lombok.Getter;
import responsecode.ICommonCode;

/**
 * 响应状态码枚举类
 */
@Getter
public enum CommonRespCodeEnum implements ICommonCode {

    SUCCESS_CODE("0000", "操作成功"), FAIL_CODE("0001", "操作失败");

    private String code;

    private String msg;

    CommonRespCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
