package dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午5:43
 */
@Component
@Setter
@Getter
public class PageDto {

    private int startNum;

    private int pageSize;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);

    }
}
