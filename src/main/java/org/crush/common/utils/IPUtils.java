package org.crush.common.utils;

/**
 * IP工具类
 *
 * @author Long Jie
 * @since 2024/11/12 17:52
 */
public class IPUtils {

    /**
     * ip address to integer
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
     * integer to ip address
     */
    public String itoa(int number) {
        return ((number >>> 24) & 0xFF) + "." +
                ((number >>> 16) & 0xFF) + "." +
                ((number >>> 8) & 0xFF) + "." +
                (number & 0xFF);
    }

}
