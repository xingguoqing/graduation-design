package util.exception;

/**
 * 业务运行时异常包装类
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误状态码
     */
    private String code;


    public BusinessRuntimeException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessRuntimeException(String code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.code = code;
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
