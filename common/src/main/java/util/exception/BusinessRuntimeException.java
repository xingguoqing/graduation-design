package util.exception;

import responsecode.ICommonCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
