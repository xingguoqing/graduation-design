package responsecode.response;

import lombok.Getter;
import responsecode.ICommonCode;
import responsecode.ICommonResponse;

/**
 * @author xingguoqing
 * @date 2018/2/2 下午2:51
 */
@Getter
public class CommonResponse implements ICommonResponse {

    public String code;

    public String msg;

    public Object datas;

    public CommonResponse(ICommonCode commonCode) {
        this.code = commonCode.getCode();
        this.msg = commonCode.getMsg();
    }

    public CommonResponse(ICommonCode commonCode,Object datas) {
        this.code = commonCode.getCode();
        this.msg = commonCode.getMsg();
        this.datas = datas;
    }


}
