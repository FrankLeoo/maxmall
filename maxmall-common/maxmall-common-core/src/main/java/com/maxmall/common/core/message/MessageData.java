package com.maxmall.common.core.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;

@Data
@NoArgsConstructor
public class MessageData {
    /**
     * ID
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 消息key
     */
    private String messageKey;

    /**
     * topic
     */
    private String messageTopic;

    /**
     * tag
     */
    private String messageTag;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 消息类型: 10 - 生产者 ; 20 - 消费者
     */
    private Integer messageType;

    /**
     * 顺序类型, 0有序 1无序
     */
    private int orderType;

    /**
     * 消息状态
     */
    private Integer status;

    /**
     * 延时级别
     */
    private int delayLevel;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近操作人
     */
    private String lastOperator;

    /**
     * 最后操作人ID
     */
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 -0 未删除 -1 已删除
     */
    private Integer yn;

    /**
     * producer group name
     */
    @Transient
    private String producerGroup;

    public MessageData(final String msgBody, final String topic, final String tag, final String key) {
        this.messageBody = msgBody;
        this.messageTopic = topic;
        this.messageTag = tag;
        this.messageKey = key;
    }


}
