package com.maxmall.provider.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.maxmall.common.base.constant.AliyunMqTopicConstants;
import com.maxmall.common.base.constant.AliyunSmsConstants;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.message.MessageData;
import com.maxmall.common.security.code.sms.SmsCodeProcessor;
import com.maxmall.common.util.PubUtils;
import com.maxmall.common.util.RandomUtil;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.dto.PcSendSmsRequest;
import com.maxmall.provider.merchant.model.dto.sms.SmsMessage;
import com.maxmall.provider.merchant.service.AccountService;
import com.maxmall.provider.merchant.service.SmsService;
import com.maxmall.zk.generator.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The class Sms service.
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Resource
    private AccountService accountService;
    @Resource(name = "smsValidateCodeProcessor")
    private SmsCodeProcessor smsCodeProcessor;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void sendSmsCode(SmsMessage smsMessage, String ipAddr) {
        Preconditions.checkArgument(smsMessage != null, "短信参数不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(ipAddr), "非法的IP地址");
        String mobileNo = smsMessage.getMobileNo();
        Preconditions.checkArgument(StringUtils.isNotEmpty(mobileNo), "手机号码不能为空");
        String smsTemplateCode = smsMessage.getSmsTemplateCode();

        Preconditions.checkArgument(AliyunSmsConstants.SmsTempletEnum.isSmsTemplate(smsTemplateCode), "短信模板没有维护");

        smsMessage.setMobileNo(mobileNo);
        smsMessage.setOutId(ipAddr);
        AliyunSmsConstants.SmsTempletEnum templetEnum = AliyunSmsConstants.SmsTempletEnum.getEnum(smsTemplateCode);
        MessageData messageData = sendSmsCodeMq(smsMessage, templetEnum);

    }

    /**
     * 提交重置密码短信验证码
     *
     * @param mobile  the mobile
     * @param request the sessionId
     * @return
     */
    @Override
    public String submitResetPwdPhone(String mobile, ServletWebRequest request) {

        Preconditions.checkArgument(StringUtils.isNotEmpty(mobile), "手机号码不能为空");
        Preconditions.checkArgument(PubUtils.isMobileNumber(mobile), "手机号码格式不正确");

        AccountDO user = accountService.findByPhone(mobile);
        if (user == null) {
            throw new UacBizException(ErrorCodeEnum.UAC10011011, mobile);
        }

        String validCode = RandomUtil.createNumberCode(6);
        //发送验证码
        try {
            smsCodeProcessor.send(request, mobile, validCode);
        } catch (Exception e) {
            throw new UacBizException(ErrorCodeEnum.OPC10040004);
        }

        String resetPwdKey = mobile + "::" + validCode;
        redisTemplate.opsForValue().set(RedisKeyUtil.getResetPwdTokenKey(resetPwdKey), user, 10, TimeUnit.MINUTES);
        return resetPwdKey;
    }

    @Override
    public MessageData sendSmsCodeMq(SmsMessage smsMessage, AliyunSmsConstants.SmsTempletEnum templetEnum) {
        log.info("sendSmsCodeMq - 发送短信验证码. smsMessage={}", smsMessage);
        String msgBody;
        try {

            PcSendSmsRequest request = new PcSendSmsRequest();
            Map<String, String> map = Maps.newHashMap();
            // 模板参数
            String smsParamName = templetEnum.getSmsParamName();
            // 模板编码
            String templetCode = templetEnum.getTempletCode();
            //替换模板验证码
            map.put(smsParamName, smsMessage.getSmsCode());
            String param = JSON.toJSONString(map);

            request.setPhoneNumbers(smsMessage.getMobileNo());
            request.setTemplateCode(templetCode);
            request.setTemplateParam(param);
            request.setOutId(smsMessage.getOutId());

            msgBody = JSON.toJSONString(request);
        } catch (Exception e) {
            log.error("发送短信验证码 smsMessage转换为json字符串失败", e);
            throw new UacBizException(ErrorCodeEnum.UAC10011022);
        }
        String topic = AliyunMqTopicConstants.MqTopicEnum.SEND_SMS_TOPIC.getTopic();
        String tag = AliyunMqTopicConstants.MqTagEnum.REGISTER_USER_AUTH_CODE.getTag();
        String key = RedisKeyUtil.createMqKey(topic, tag, smsMessage.getMobileNo(), msgBody);
        return new MessageData(msgBody, topic, tag, key);
    }
}
