/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.reflect;

public class NoSuchFieldException extends ReflectionException {
    public NoSuchFieldException() {}

    public NoSuchFieldException(String message) {
        super(message);
    }

    public NoSuchFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
