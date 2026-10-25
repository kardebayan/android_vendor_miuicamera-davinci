/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.miui.internal.log.util;

import android.os.Environment;

public class Config {
    public static String getDefaultCacheLogDir() {
        return Environment.getExternalStorageDirectory() + "/MIUI/debug_log/";
    }
}
