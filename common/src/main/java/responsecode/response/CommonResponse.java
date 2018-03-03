package responsecode.response;

import lombok.Getter;
import responsecode.ICommonCode;
import responsecode.ICommonResponse;

/**
 * @author xingguoqing
 * @date 2018/2/2 下午2:51
 */

public class CommonResponse implements ICommonResponse {

    public String code;

    public String msg;

    public Object datas;

    public CommonResponse(ICommonCode commonCode) {
        this.code = commonCode.getCode();
        this.msg = commonCode.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getDatas() {
        return datas;
    }

    public CommonResponse(ICommonCode commonCode, Object datas) {
        this.code = commonCode.getCode();
        this.msg = commonCode.getMsg();
        this.datas = datas;
    }


}
