/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class ExpoEaseOutInterpolater implements Interpolator {
    @Override
    public float getInterpolation(float t) {
        if (t == 1.0f) {
            return 1.0f;
        }
        return (float) (-Math.pow(2.0d, -10.0f * t) + 1.0d);
    }
}
