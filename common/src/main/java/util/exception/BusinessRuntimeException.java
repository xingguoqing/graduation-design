package util.exception;

import response.ResponseCodeEnum;

/**
 * 业务运行时异常包装类
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;


    public BusinessRuntimeException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
