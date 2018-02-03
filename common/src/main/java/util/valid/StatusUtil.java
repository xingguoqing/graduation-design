package util.valid;

import enums.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import responsecode.enums.StatusCodeEnum;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午9:26
 */
public class StatusUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusUtil.class);

    public static void validStatus(String code)  {
        LOGGER.info("开始校验状态");
        StatusEnum statusEnum = StatusEnum.getStatusEnum(code);
        if (statusEnum != null){
            LOGGER.info("状态校验通过");
            return;
        }
        LOGGER.error("校验状态失败，非法的状态:{}",code);
        BusinessRuntimeException.wrapBusiException(StatusCodeEnum.STATUS_ILLEGAL);
    }
}
