package util.valid;

import constant.PageConstant;
import dto.PageDto;
import dto.PageResultDto;
import responsecode.enums.PageCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * 分页校验工具类
 * @date 2018/2/2 下午1:50
 */
public class PageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtil.class);

    public static void validParams(int pageNum, int pageSize) {

        LOGGER.info("开始校验分页参数");
        if (pageNum < PageConstant.PAGENUM_MINIMUM) {
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGENUM_ILLEGAL);
        }
        if (pageSize < PageConstant.PAGESIZE_MINIMUM) {
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGESIZE_ILLEGAL);
        }
        if (pageSize > PageConstant.PAGESIZE_MAXIMUM) {
            BusinessRuntimeException.wrapBusiException(PageCodeEnum.PAGESIZE_TOOMUCH);
        }
        LOGGER.info("分页参数校验通过:pageNum:{},pageSize{}",pageNum,pageSize);
    }

    public static PageDto getPageDto(int pageNum, int pageSize) {

        LOGGER.info("开始转换分页参数");
        PageDto pageDto = new PageDto();
        pageDto.setStartNum((pageNum - 1) * pageSize);
        pageDto.setPageSize(pageSize);
        LOGGER.info("分页参数转换完成:{}",pageDto);
        return pageDto;
    }
}
