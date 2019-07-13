package com.maxmall.common.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

/**
 * Bean转换器
 *
 * @author : 晓岚<jisen@startdt.com>
 * @date : 2017-11-16 下午10:16
 */
public class BeanConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConverter.class);

    /**
     * Bean转换
     *
     * @param source 源对象
     * @param clz    目标对象类型
     * @param <T>    T
     * @return 转换后的对象
     */
    public static <T> T convert(Object source, Class<T> clz) {
        if (source != null) {
            BeanCopier copier = BeanCopier.create(source.getClass(), clz, false);
            try {
                T target = clz.newInstance();
                copier.copy(source, target, null);
                return target;
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("对象转换异常:{}", e);
            }
        }
        return null;
    }

    public static <T> List<T> batchConvert(List<?> sources, Class<T> clz) {
        List<T> targets = new ArrayList();
        for (Object source : sources) {
            T target = convert(source, clz);
            targets.add(target);
        }
        return targets;
    }

    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

}
