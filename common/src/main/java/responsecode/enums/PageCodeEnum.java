package responsecode.enums;



import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/2 下午1:34
 */
public enum PageCodeEnum implements ICommonCode {

    PAGENUM_ILLEGAL("1001", "分页页数不合法"), PAGESIZE_ILLEGAL("1002", "分页大小不合法"), PAGESIZE_TOOMUCH(
        "1003", "分页大小太大");

    private String code;

    private String msg;

    PageCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


}
