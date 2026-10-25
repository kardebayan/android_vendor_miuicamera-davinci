/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.reflect;

import java.util.ArrayList;
import java.util.List;

/** Resolves JNI type signatures, as the MIUI SDK does natively. */
final class ReflectUtils {
    static Class<?> loadClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new NoSuchClassException(name, e);
        }
    }

    static Class<?> typeOf(String signature) {
        return parse(signature, new int[] {0});
    }

    static Class<?>[] parameterTypesOf(String methodSignature) {
        List<Class<?>> types = new ArrayList<>();
        int[] pos = {methodSignature.indexOf('(') + 1};
        while (methodSignature.charAt(pos[0]) != ')') {
            types.add(parse(methodSignature, pos));
        }
        return types.toArray(new Class<?>[0]);
    }

    private static Class<?> parse(String sig, int[] pos) {
        char c = sig.charAt(pos[0]++);
        switch (c) {
            case 'Z': return boolean.class;
            case 'B': return byte.class;
            case 'C': return char.class;
            case 'S': return short.class;
            case 'I': return int.class;
            case 'J': return long.class;
            case 'F': return float.class;
            case 'D': return double.class;
            case 'V': return void.class;
            case 'L': {
                int end = sig.indexOf(';', pos[0]);
                String name = sig.substring(pos[0], end).replace('/', '.');
                pos[0] = end + 1;
                return loadClass(name);
            }
            case '[': {
                int start = pos[0] - 1;
                parse(sig, pos);
                return loadClass(sig.substring(start, pos[0]).replace('/', '.'));
            }
            default:
                throw new IllegalArgumentException("Bad signature " + sig);
        }
    }
}
