package com.mrqinzh.common.utils;

import com.mrqinzh.common.exception.BizException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;

public class BizAssert extends Assert {



    public static void isFalse(boolean expression, String msg) {
        isTrue(!expression, msg);
    }

    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throw new BizException(msg);
        }
    }

    public static void isNull(Object obj, String msg) {
        if (obj != null) throw new BizException(msg);
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) throw new BizException(msg);
    }

    public static void isBlank(String msg, String str) {
        if (Strings.isNotBlank(str)) throw new BizException(msg);
    }
    public static void isBlank(String msg, String... str) {
        for (String s : str) {
            isBlank(msg, s);
        }
    }

    public static void isNotBlank(String msg, String str) {
        if (Strings.isBlank(str)) throw new BizException(msg);
    }
    public static void isNotBlank(String msg, String... str) {
        for (String s : str) {
            isNotBlank(msg, s);
        }
    }

}
