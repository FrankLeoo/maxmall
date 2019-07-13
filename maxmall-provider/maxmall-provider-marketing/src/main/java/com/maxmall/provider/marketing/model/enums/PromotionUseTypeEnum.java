package com.maxmall.provider.marketing.model.enums;

public enum PromotionUseTypeEnum {

    ALL(0,"全场通用"),
    CATEGORY(1,"指定分类"),
    PRODUCT(2,"指定商品"),;

    /**
     * 状态
     */
    private int type;
    private String desc;

    PromotionUseTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param type
     * @return
     */
    public static PromotionUseTypeEnum getByType(int type) {
        for (PromotionUseTypeEnum statusEnum : values()) {
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
