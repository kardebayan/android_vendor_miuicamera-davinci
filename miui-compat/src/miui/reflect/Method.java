/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.reflect;

import java.lang.reflect.InvocationTargetException;

/** Java reflection backed replacement for the MIUI SDK native method invoker. */
public class Method {
    private final java.lang.reflect.Method mMethod;

    private Method(java.lang.reflect.Method method) {
        mMethod = method;
        mMethod.setAccessible(true);
    }

    public static Method of(Class<?> cls, String name, String signature) {
        Class<?>[] parameterTypes = ReflectUtils.parameterTypesOf(signature);
        try {
            return new Method(cls.getDeclaredMethod(name, parameterTypes));
        } catch (java.lang.NoSuchMethodException e) {
            throw new NoSuchMethodException(
                    "Method " + name + signature + " not found in " + cls.getName(), e);
        }
    }

    public void invoke(Class<?> cls, Object obj, Object... args) {
        call(obj, args);
    }

    public boolean invokeBoolean(Class<?> cls, Object obj, Object... args) {
        return (Boolean) call(obj, args);
    }

    private Object call(Object obj, Object[] args) {
        try {
            return mMethod.invoke(obj, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new IllegalArgumentException(cause);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
