package com.maxmall.provider.order.model.enums;

public enum  ApplyStatusEnum {

    WAIT(0,"待处理"),
    REFUND(1,"退货中"),
    FINISH(2,"已完成"),
    REFUSE(3,"已拒绝");

    /**
     * 状态
     */
    private int status;
    private String desc;

    ApplyStatusEnum(int status,String desc){
        this.status = status;
        this.desc = desc;
    }

    /**
     * 根据状态获取 enum
     * @param status
     * @return
     */
    public static ApplyStatusEnum getByStatus(int status) {
        for (ApplyStatusEnum statusEnum : values()) {
            if (statusEnum.getStatus() == status) {
                return statusEnum;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
