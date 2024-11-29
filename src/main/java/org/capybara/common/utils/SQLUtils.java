package org.capybara.common.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL工具类
 *
 * @author Long Jie
 * @since 2024/11/29 9:15
 */
public class SQLUtils {

    /**
     * 读入一个SQL文件，将里面的所有SQ语句L切分成一条条语句
     *
     * @param is sql file
     * @return 每个元素都是一条单独的SQL语句
     */
    public List<String> split(InputStream is) {
        return new ArrayList<>();
    }

}
