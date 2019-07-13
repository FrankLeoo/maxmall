package com.maxmall.provider.order.model.enums;

public enum ExpressTypeEnum {


    MANUAL(0,"手动发货"),
    AUTOMATIC(1,"系统自动发货"),;

    /**
     * 状态
     */
    private int type;
    private String desc;

    ExpressTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param type
     * @return
     */
    public static ExpressTypeEnum getByType(int type) {
        for (ExpressTypeEnum statusEnum : values()) {
            if (statusEnum.getType() == type) {
                return statusEnum;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
