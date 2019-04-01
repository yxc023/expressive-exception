package com.yangxiaochen.exception.core.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author yangxiaochen
 * @Date 2018-08-24
 */
public abstract class Msg {
    public static String m(String message, Object...args) {
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }
}
