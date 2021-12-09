package com.snow.schedule.demo03.utils;

import java.lang.reflect.Field;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 10:22
 */
public class BeanUtils {
    /**
     * 获取属性
     *
     * @param obj
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Object getProperty(Object obj, String name) throws NoSuchFieldException {
        Object value = null;
        Field field = findField(obj.getClass(), name);
        if (field == null) {
            throw new NoSuchFieldException("no such field [" + name + "]");
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            value = field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(accessible);
        return value;
    }

    public static Field findField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException ex) {
            return findDeclaredField(clazz, name);
        }
    }

    public static Field findDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }

}
