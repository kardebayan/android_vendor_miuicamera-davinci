/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.view.animation;

import android.view.animation.Interpolator;

public class QuarticEaseOutInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float t) {
        float x = t - 1.0f;
        return -(x * x * x * x - 1.0f);
    }
}
