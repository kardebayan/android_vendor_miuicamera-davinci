/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package android.provider;

import android.content.ContentResolver;

public class MiuiSettings {
    public static class Global {
        public static boolean getBoolean(ContentResolver resolver, String name) {
            return Settings.Global.getInt(resolver, name, 0) != 0;
        }
    }

    public static class System {
        public static String getString(ContentResolver resolver, String name) {
            return Settings.System.getString(resolver, name);
        }
    }

    /** Xiaomi cloud controlled settings, always at their defaults on AOSP. */
    public static class SettingsCloudData {
        public static int getCloudDataInt(ContentResolver resolver, String module, String key,
                int defaultValue) {
            return defaultValue;
        }

        public static String getCloudDataString(ContentResolver resolver, String module,
                String key, String defaultValue) {
            return defaultValue;
        }
    }
}
