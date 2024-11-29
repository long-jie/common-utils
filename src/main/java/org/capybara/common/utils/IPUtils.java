package org.capybara.common.utils;

/**
 * IP工具类
 *
 * @author Long Jie
 * @since 2024/11/12 17:52
 */
public class IPUtils {

    /**
     * 将 xxx.xxx.xxx.xxx 格式的IP地址转换成一个整数值
     *
     * @param address xxx.xxx.xxx.xxx格式的IP地址
     * @return int value
     */
    public int atoi(String address) {
        String[] parts = address.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IP address: " + address);
        }
        int result = 0;
        for (String part : parts) {
            short value = Short.parseShort(part);
            result = (result << 8) | value;
        }
        return result;
    }

    /**
     * 将一个整数值转换成IP地址
     *
     * @param number IP地址的整数值
     * @return xxx.xxx.xxx.xxx格式的IP地址
     */
    public String itoa(int number) {
        return ((number >>> 24) & 0xFF) + "." +
                ((number >>> 16) & 0xFF) + "." +
                ((number >>> 8) & 0xFF) + "." +
                (number & 0xFF);
    }

}
