package enums;

import lombok.Getter;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午9:22
 */
@Getter
public enum StatusEnum {

    ABLE("Y","启动"),ENABLE("N","禁用");

    private String code;

    private String msg;

    StatusEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
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
