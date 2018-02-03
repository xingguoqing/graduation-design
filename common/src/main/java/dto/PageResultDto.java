package dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午5:28
 */
@Component
@Setter
@Getter
public class PageResultDto {

    private int count;

    private Object datas;
}
