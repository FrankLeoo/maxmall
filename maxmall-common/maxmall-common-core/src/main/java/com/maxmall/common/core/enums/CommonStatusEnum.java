package com.maxmall.common.core.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ivoter
 * @ClassName CommonStatusEnum.java
 * @date 2019/05/22 11:13:00
 * @Description 状态枚举
 */
public enum CommonStatusEnum {

    /**
     * 启用
     */
    ENABLE(0, "启用"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用"),
    /**
     * 审核中
     */
    AUDIT(2, "审核中"),
    ;

    /**
     * The Status.
     */
    int status;
    /**
     * The Value.
     */
    String message;

    /**
     * Gets name.
     *
     * @param status the status
     *
     * @return the name
     */
    public static String getName(int status) {
        for (CommonStatusEnum ele : CommonStatusEnum.values()) {
            if (status == ele.getStatus()) {
                return ele.getMessage();
            }
        }
        return null;
    }

    /**
     * 根据状态获取 enum
     * @param status
     * @return
     */
    public static CommonStatusEnum getByStatus(int status) {
        for (CommonStatusEnum statusEnum : values()) {
            if (statusEnum.getStatus() == status) {
                return statusEnum;
            }
        }
        return null;
    }

    CommonStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getMessage() {
        return message;
    }

    private static List<Integer> getStatusList() {
        List<Integer> list = Lists.newArrayList();
        for (CommonStatusEnum ele : CommonStatusEnum.values()) {
            list.add(ele.getStatus());
        }
        return list;
    }

    /**
     * Contains boolean.
     *
     * @param status the status
     *
     * @return the boolean
     */
    public static boolean contains(Integer status) {
        List<Integer> statusList = getStatusList();
        return statusList.contains(status);
    }
}
