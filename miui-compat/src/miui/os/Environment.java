/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.os;

import java.io.File;

public class Environment {
    public static File getExternalStorageDirectory() {
        return android.os.Environment.getExternalStorageDirectory();
    }
}
