/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package android.media;

import android.content.Context;

/** MIUI audio zoom parameters, unsupported on AOSP. init() reports failure so the app skips it. */
public class AudioParaManger {
    public AudioParaManger(MediaRecorder recorder, Context context) {}

    public boolean init(int a, int b, int c, double d, double e, double f, double g, double h,
            boolean i) {
        return false;
    }

    public void prepare() {}

    public void start() {}

    public void setAudioFocusAzimuth(double value) {}

    public void setAudioFocusElevation(double value) {}

    public void setAudioFocusHeight(double value) {}

    public void setAudioFocusWidth(double value) {}

    public void setAudioWindNoise(boolean enabled) {}

    public void setAudioZoomLevel(double value) {}

    public void setMaxSupportLevel(int level) {}

    public void setRecordType(int type) {}
}
