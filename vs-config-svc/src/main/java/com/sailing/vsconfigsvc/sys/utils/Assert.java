package com.sailing.vsconfigsvc.sys.utils;

import com.sailing.vsconfigsvc.common.exception.VsConfigException;
import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 断言，非参数验证，如逻辑类验证可使用此方法
 * @Author: gaowei
 * @Date: 2018-08-30
 * @version: 1.0
 */
public class Assert extends org.springframework.util.Assert {
    private final static int DEFAULT_CODE = 211;

    public static boolean ObjectEquals(Object obj1, Object obj2) {
        if (obj1.getClass() != obj2.getClass()) {
            return false;
        }
        boolean rtn = false;
        if (obj1 instanceof String || obj1 instanceof Integer || obj1 instanceof Float || obj1 instanceof DateTime
                || obj1 instanceof Long) {
            if (obj1.toString().equals(obj1.toString())) {
                rtn = true;
            }
        } else {
            rtn = ObjectUtils.nullSafeEquals(obj1, obj2);
        }
        return rtn;
    }

    public static void state(boolean expression, int code, String message) {
        if (!expression) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void error(String message) {
        throw new VsConfigException(String.valueOf(DEFAULT_CODE), message);
    }

    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void isNull(Object object, int code, String message) {
        if (object != null) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void isBlank(String str, int code, String message) {
        if (!StringUtils.hasLength(str)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void isBlank(String str, String message) {
        if (!StringUtils.hasLength(str)) {
            throw new VsConfigException(String.valueOf(DEFAULT_CODE), message);
        }
    }

    public static void notNull(Object object, int code, String message) {
        if (object == null) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void hasLength(String text, int code, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void hasText(String text, int code, String message) {
        if (!StringUtils.hasText(text)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, int code, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
                && textToSearch.contains(substring)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void notEmpty(Object object, String message) {
        notEmpty(object, DEFAULT_CODE, message);
    }

    public static void notEmpty(Object object, int code, String message) {
        if (ObjectUtils.isEmpty(object)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void notEmpty(Object[] array, int code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void noNullElements(Object[] array, int code, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new VsConfigException(String.valueOf(code), message);
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    public static void notEmpty(Map<?, ?> map, int code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    /**
     * 集合中是否存在元素
     *
     * @param collection
     * @param code
     * @param message
     */
    public static void noElement(Collection<?> collection, int code, String message) {
        notNull(collection, code, message);
        if (collection.size() <= 0) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    /**
     * 集合中是否存在元素
     *
     * @param collection
     * @param message
     */
    public static void noElement(Collection<?> collection, String message) {
        notNull(collection, DEFAULT_CODE, message);
        if (collection.size() <= 0) {
            throw new VsConfigException(String.valueOf(DEFAULT_CODE), message);
        }
    }

    /**
     * list中是否包含某元素，若不包含则验证失败
     *
     * @param list
     * @param value
     * @param code
     * @param message
     */
    public static void notIn(Collection<?> list, Object value, int code, String message) {
        boolean in = false;
        for (Object obj : list) {
            in = ObjectUtils.nullSafeEquals(obj, value);
            if (in) {
                break;
            }
        }
        if (!in) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    /**
     * list中是否包含某元素，若不包含则验证失败
     *
     * @param list
     * @param value
     * @param message
     */
    public static void notIn(Collection<?> list, Object value, String message) {
        notIn(list, value, DEFAULT_CODE, message);
    }

    /**
     * Map的值中是否包含某元素，若不包含则验证失败
     *
     * @param map
     * @param value
     * @param message
     */
    public static void notIn(Map<String, Object> map, Object value, int code, String message) {
        boolean in = false;
        for (Object obj : map.entrySet()) {
            in = ObjectUtils.nullSafeEquals(obj, value);
            if (in) {
                break;
            }
        }
        if (!in) {
            throw new VsConfigException(String.valueOf(code), message);
        }
    }

    /**
     * Map的值中是否包含某元素，若不包含则验证失败
     *
     * @param map
     * @param value
     * @param message
     */
    public static void notIn(Map<String, Object> map, Object value, String message) {
        notIn(map, value, DEFAULT_CODE, message);
    }

    /**
     * 数组中是否包含某元素，若不包含则验证失败
     *
     * @param objects
     * @param object
     * @param message
     */
    public static void notIn(Object[] objects, Object object, int code, String message) {
        boolean in = false;
        for (Object obj : objects) {
            in = ObjectUtils.nullSafeEquals(obj, object);
            if (in) {
                break;
            }
        }
        if (!in) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 数组中是否包含某元素，若不包含则验证失败
     *
     * @param objects
     * @param object
     * @param message
     */
    public static void notIn(Object[] objects, Object object, String message) {
        notIn(objects, object, DEFAULT_CODE, message);
    }

    /**
     * map是否存在key，若不存在则返验证失败
     *
     * @param map
     * @param key
     * @param message
     */
    public static void notExist(Map<String, Object> map, String key, int code, String message) {
        if (map.containsKey(key)) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * map是否存在key，若不存在则返验证失败
     *
     * @param map
     * @param key
     * @param message
     */
    public static void notExist(Map<String, Object> map, String key, String message) {
        notExist(map, key, DEFAULT_CODE, message);
    }

    /**
     * 集合中是否存在空元素，若存在验证失败
     *
     * @param collection
     * @param message
     */
    public static void noNullElements(Collection<?> collection, int code, String message) {
        if (collection != null) {
            for (Object element : collection) {
                if (ObjectUtils.isEmpty(element)) {
                    throw new VsConfigException("-1",message);
                }
            }
        } else {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 集合中是否存在空元素，若存在验证失败
     *
     * @param collection
     * @param message
     */
    public static void noNullElements(Collection<?> collection, String message) {
        noNullElements(collection, DEFAULT_CODE, message);
    }

    /**
     * map中是否存在空元素，若存在验证失败
     *
     * @param map
     * @param message
     */
    public static void noNullElements(Map<String, Object> map, int code, String message) {
        if (map != null) {
            for (Object element : map.entrySet()) {
                if (ObjectUtils.isEmpty(element)) {
                    throw new VsConfigException("-1",message);
                }
            }
        } else {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * map中是否存在空元素，若存在验证失败
     *
     * @param map
     * @param message
     */
    public static void noNullElements(Map<String, Object> map, String message) {
        noNullElements(map, DEFAULT_CODE, message);
    }

    /**
     * 文本长度大于length则抛出异常
     *
     * @param text
     * @param length
     * @param message
     */
    public static void gtLength(String text, int length, int code, String message) {
        notNull(text, message);
        if (text.length() > length) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 文本长度大于length则抛出异常
     *
     * @param text
     * @param length
     * @param message
     */
    public static void gtLength(String text, int length, String message) {
        gtLength(text, length, DEFAULT_CODE, message);
    }

    /**
     * 文本长度小于length则抛出异常
     *
     * @param text
     * @param length
     * @param message
     */
    public static void ltLength(String text, int length, int code, String message) {
        hasText(text, message);
        if (text.length() < length) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 文本长度小于length则抛出异常
     *
     * @param text
     * @param length
     * @param message
     */
    public static void ltLength(String text, int length, String message) {
        ltLength(text, length, DEFAULT_CODE, message);
    }

    /**
     * 文本长度不在min,max之间则抛出异常
     *
     * @param text
     * @param min
     * @param max
     * @param message
     */
    public static void noBetweenLength(String text, int min, int max, int code, String message) {
        notNull(text, message);
        if (text.length() < min || text.length() > max) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 文本长度不在min,max之间则抛出异常
     *
     * @param text
     * @param min
     * @param max
     * @param message
     */
    public static void noBetweenLength(String text, int min, int max, String message) {
        noBetweenLength(text, min, max, DEFAULT_CODE, message);
    }

    /**
     * 文本长度在min,max之间则(包括min和max)抛出异常
     *
     * @param text
     * @param min
     * @param max
     * @param message
     */
    public static void betweenLength(String text, int min, int max, int code, String message) {
        notNull(text, message);
        if (text.length() >= min || text.length() <= max) {
            throw new VsConfigException("-1",message);
        }
    }

    /**
     * 文本长度在min,max之间则(包括min和max)抛出异常
     *
     * @param text
     * @param min
     * @param max
     * @param message
     */
    public static void betweenLength(String text, int min, int max, String message) {
        betweenLength(text, min, max, DEFAULT_CODE, message);
    }

    private static void createBindException(Object obj, Set<ConstraintViolation<Object>> violations)
            throws BindException {
        if (violations.size() > 0) {
            BindException bindException = new BindException(obj, obj.getClass().getName());
            violations.forEach(item -> {
                String filed = item.getPropertyPath().toString();
                bindException.rejectValue(filed, filed, item.getMessage());
            });
            throw bindException;
        }
    }

    /**
     * 使用 Validation 手动验证，等同于@Valid注解
     *
     * @param obj
     */
    public static void validate(Object obj) throws BindException {
        Set<ConstraintViolation<Object>> violations = Validation.buildDefaultValidatorFactory().getValidator()
                .validate(obj);
        createBindException(obj, violations);
    }

    /**
     * 使用 Validation 手动分组验证，等同于@Valid注解
     *
     * @param obj   校验目标
     * @param clazz 分组
     * @throws BindException
     */
    public static void validate(Object obj, Class<?>... clazz) throws BindException {
        Set<ConstraintViolation<Object>> violations = Validation.buildDefaultValidatorFactory().getValidator()
                .validate(obj, clazz);
        createBindException(obj, violations);
    }
}