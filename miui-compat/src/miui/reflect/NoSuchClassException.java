/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.reflect;

public class NoSuchClassException extends ReflectionException {
    public NoSuchClassException() {}

    public NoSuchClassException(String message) {
        super(message);
    }

    public NoSuchClassException(String message, Throwable cause) {
        super(message, cause);
    }
}
