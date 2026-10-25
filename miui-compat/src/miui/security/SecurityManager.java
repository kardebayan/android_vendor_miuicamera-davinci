/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.security;

/** MIUI app lock, never enabled on AOSP. */
public class SecurityManager {
    public boolean getApplicationAccessControlEnabled(String packageName) {
        return false;
    }

    public boolean checkAccessControlPass(String packageName) {
        return true;
    }
}
