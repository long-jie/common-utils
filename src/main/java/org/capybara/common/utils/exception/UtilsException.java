package org.capybara.common.utils.exception;

/**
 * 工具包异常
 *
 * @author Long Jie
 * @since 2024/12/2 7:14
 */
public class UtilsException extends RuntimeException {

    public UtilsException(String message) {
        super(message);
    }

    public UtilsException(String message, Throwable cause) {
        super(message, cause);
    }

}
