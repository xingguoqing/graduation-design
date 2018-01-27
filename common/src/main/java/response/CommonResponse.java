package response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponse {

    private String code;

    private String msg;

    private Object datas;

    public CommonResponse() {
    }

    public static CommonResponse genSuccessResult(Object datas) {
        return new CommonResponse(ResponseCodeEnum.SUCCESS_CODE.getCode(), "SUCCESS", datas);
    }

    public CommonResponse(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public CommonResponse(String code, String msg, Object datas) {
        this.setCode(code);
        this.setMsg(msg);
        this.setDatas(datas);
    }

}
