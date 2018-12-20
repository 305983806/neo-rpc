package com.neo.rpc.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:20
 * @Description: 串工具类
 */
public class StringUtil {
    /**、
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     * @param str 目标字符串
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 分割固定格式的字符串
     * @param str 目标字符串
     * @param separator 分隔符
     * @return
     */
    public static String[] split(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
