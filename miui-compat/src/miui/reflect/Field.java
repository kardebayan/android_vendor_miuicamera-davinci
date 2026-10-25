/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.reflect;

/** Java reflection backed replacement for the MIUI SDK native field accessor. */
public class Field {
    private final java.lang.reflect.Field mField;

    private Field(java.lang.reflect.Field field) {
        mField = field;
        mField.setAccessible(true);
    }

    public static Field of(Class<?> cls, String name, String signature) {
        Class<?> type = ReflectUtils.typeOf(signature);
        for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
            try {
                java.lang.reflect.Field field = c.getDeclaredField(name);
                if (field.getType() == type) {
                    return new Field(field);
                }
            } catch (java.lang.NoSuchFieldException e) {
                // Try the superclass
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + cls.getName());
    }

    public static Field of(String className, String name, String signature) {
        return of(ReflectUtils.loadClass(className), name, signature);
    }

    public Object get(Object obj) {
        try {
            return mField.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int getInt(Object obj) {
        try {
            return mField.getInt(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
