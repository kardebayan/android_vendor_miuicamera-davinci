/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.util;

import android.content.Context;
import android.net.Uri;
import android.os.SystemProperties;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;

/** MIUI haptics. Effects map to the AOSP tick, MIUI linear motor waveforms are unavailable. */
public class HapticFeedbackUtil {
    private final Context mContext;
    private final Vibrator mVibrator;

    public HapticFeedbackUtil(Context context, boolean onceOnly) {
        mContext = context;
        mVibrator = context.getSystemService(Vibrator.class);
    }

    public static boolean isSupportLinearMotorVibrate() {
        return "linear".equals(SystemProperties.get("sys.haptic.motor"));
    }

    public static boolean isSupportLinearMotorVibrate(int effectId) {
        return false;
    }

    public boolean isSupportExtHapticFeedback(int effectId) {
        return false;
    }

    public boolean performExtHapticFeedback(int effectId) {
        return false;
    }

    public boolean performExtHapticFeedback(Uri uri) {
        return false;
    }

    public boolean performHapticFeedback(int effectId, boolean always) {
        return vibrate(always);
    }

    public boolean performHapticFeedback(int effectId, boolean always, int effectStrength) {
        return vibrate(always);
    }

    public boolean performHapticFeedback(int effectId, double intensity, String reason) {
        return vibrate(false);
    }

    public boolean performHapticFeedback(String key, boolean always) {
        return vibrate(always);
    }

    public boolean performHapticFeedback(String key, boolean always, int effectStrength) {
        return vibrate(always);
    }

    public void release() {
        if (mVibrator != null) {
            mVibrator.cancel();
        }
    }

    private boolean vibrate(boolean always) {
        if (mVibrator == null || !mVibrator.hasVibrator()) {
            return false;
        }
        if (!always && Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.HAPTIC_FEEDBACK_ENABLED, 1) == 0) {
            return false;
        }
        mVibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK));
        return true;
    }
}
