package util.exception;

import org.springframework.util.StringUtils;
import responsecode.ICommonCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;

/**
 * 业务运行时异常包装类
 */
@Setter
@Getter
public class BusinessRuntimeException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRuntimeException.class);

    private ICommonCode errorCode;


    public BusinessRuntimeException(ICommonCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public static void wrapBusiException(ICommonCode errorCode) {
        LOGGER.error(errorCode.getMsg());
        throw new BusinessRuntimeException(errorCode);
    }

    public static ICommonResponse responseException(Throwable e,String msg){
        Throwable cause = unmarsh(e);
        LOGGER.error(msg + ":" + cause.getMessage(), cause);
        if (cause instanceof BusinessRuntimeException) {
            BusinessRuntimeException be = (BusinessRuntimeException) cause;
            if (!StringUtils.isEmpty(be.getErrorCode())) {
                return new CommonResponse(be.getErrorCode());
            }
        }
        return new CommonResponse(CommonRespCodeEnum.FAIL_CODE);
    }

    /**
     * 异常拆箱
     *
     * @param ex
     * @return
     */
    public static Throwable unmarsh(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause != null) {
            cause = unmarsh(cause);
        } else {
            cause = ex;
        }
        return cause;
    }

}
