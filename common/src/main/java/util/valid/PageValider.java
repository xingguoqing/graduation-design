package util.valid;

import constant.PageConstant;
import responsecode.enums.PageCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * 分页校验工具类
 * @date 2018/2/2 下午1:50
 */
public class PageValider {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageValider.class);

    public static void validParams(int pageNum, int pageSize) {

        if(pageNum< PageConstant.PAGENUM_MINIMUM){
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGENUM_ILLEGAL);
        }
        if(pageSize<PageConstant.PAGESIZE_MINIMUM){
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGESIZE_ILLEGAL);
        }
        if(pageSize>PageConstant.PAGESIZE_MAXIMUM){
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGESIZE_TOOMUCH);
        }
    }
}
