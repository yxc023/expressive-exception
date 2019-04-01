package com.yangxiaochen.exception.core;

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
