package util.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * 分页工具类
 */
@Component
@Setter
@Getter
public class PageUtil<T> {

    private int pageSize;

    private int pageNum;

    private String key;

    private T o;
}
