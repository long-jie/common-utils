package org.capybara.common.utils.exception;

/**
 * @author Long Jie
 * @since 2024/12/6 13:06
 */
public class ExceptionUtils {

    public static void throwRuntimeException(String message, Throwable e) {
        if (e instanceof UtilsException) {
            throw (UtilsException) e;
        } else {
            throw new UtilsException(message, e);
        }
    }

}
