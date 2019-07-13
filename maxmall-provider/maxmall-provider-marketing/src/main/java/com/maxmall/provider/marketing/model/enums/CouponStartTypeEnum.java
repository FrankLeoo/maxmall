package com.maxmall.provider.marketing.model.enums;

public enum CouponStartTypeEnum {

    NORMAL(0,"固定时间"),
    AFTER_GIVE(1,"领取后几天"),;

    /**
     * 状态
     */
    private int type;
    private String desc;

    CouponStartTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param type
     * @return
     */
    public static CouponStartTypeEnum getByType(int type) {
        for (CouponStartTypeEnum statusEnum : values()) {
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
