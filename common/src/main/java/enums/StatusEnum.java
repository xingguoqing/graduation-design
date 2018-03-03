package enums;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午9:22
 */
public enum StatusEnum implements ICommonCode{

    ABLE("Y","启动"),ENABLE("N","禁用");

    private String code;

    private String msg;

    StatusEnum(String code,String msg){
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

    public static StatusEnum getStatusEnum(String code){
        for(StatusEnum statusEnum:StatusEnum.values()){
            if(statusEnum.getCode().equals(code)){
                return  statusEnum;
            }
        }
        return null;
    }

}
