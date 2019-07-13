package com.maxmall.provider.order.model.enums;

public enum ReceiveTypeEnum {


    NO_RECEIVE(0,"无需退货"),
    CUSTOMER_SEND(1,"客户回递"),;

    /**
     * 状态
     */
    private int type;
    private String desc;

    ReceiveTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param type
     * @return
     */
    public static ReceiveTypeEnum getByType(int type) {
        for (ReceiveTypeEnum statusEnum : values()) {
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
