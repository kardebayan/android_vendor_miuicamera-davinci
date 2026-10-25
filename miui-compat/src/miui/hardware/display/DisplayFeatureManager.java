/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.hardware.display;

/** MIUI display effects (e.g. camera mode color), unavailable on AOSP. */
public class DisplayFeatureManager {
    private static final DisplayFeatureManager sInstance = new DisplayFeatureManager();

    public static DisplayFeatureManager getInstance() {
        return sInstance;
    }

    public void setScreenEffect(int mode, int value) {}
}
