package com.maxmall.common.util.converter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * code生成器
 *
 * @author : 晓岚<jisen@startdt.com>
 * @date : 2017-11-17 下午4:00
 */
public class CodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);

    private static String currentIp = null;

    public static String generateCode() {
        if (currentIp == null) {
            try {
                currentIp = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                LOGGER.error("获取本地ip失败:", e);
            }
        }
        long now = System.nanoTime();

        long threadId = Thread.currentThread().getId();
        long ipNumber = ipToNumber(currentIp);
        String uniqueStr = "" + ipNumber + now + threadId + "";
        BigInteger bi = new BigInteger(uniqueStr);
        return bi.toString(36);
    }

    private static long ipToNumber(String ip) {
        if (StringUtils.isEmpty(ip)){
            return 0;
        }
        long result = 0;
        String[] ipAddressInArray = ip.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long value = Long.parseLong(ipAddressInArray[3 - i]);
            result |= value << (i * 8);

        }
        return result;
    }
}
