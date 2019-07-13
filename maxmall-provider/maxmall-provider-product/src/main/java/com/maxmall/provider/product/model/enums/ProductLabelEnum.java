package com.maxmall.provider.product.model.enums;

public enum ProductLabelEnum {

    HOT("hot","待处理"),
    NEW("new","退货中"),
    RECOMMAND("recommand","已拒绝");

    /**
     * 状态
     */
    private String type;
    private String desc;

    ProductLabelEnum(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param type
     * @return
     */
    public static ProductLabelEnum getByType(String type) {
        for (ProductLabelEnum statusEnum : values()) {
            if (statusEnum.getType().equals(type)) {
                return statusEnum;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
