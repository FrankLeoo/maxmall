package com.maxmall.common.core.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ivoter
 * @ClassName CommonStatusEnum.java
 * @date 2019/05/22 11:13:00
 * @Description 状态枚举
 */
public enum TrueOrFalseEnum {

    /**
     * 启用
     */
    TRUE(1, "正确"),
    /**
     * 禁用
     */
    FALSE(0, "错误"),
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
        for (TrueOrFalseEnum ele : TrueOrFalseEnum.values()) {
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
    public static TrueOrFalseEnum getByStatus(int status) {
        for (TrueOrFalseEnum statusEnum : values()) {
            if (statusEnum.getStatus() == status) {
                return statusEnum;
            }
        }
        return null;
    }

    TrueOrFalseEnum(int status, String message) {
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
        for (TrueOrFalseEnum ele : TrueOrFalseEnum.values()) {
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
