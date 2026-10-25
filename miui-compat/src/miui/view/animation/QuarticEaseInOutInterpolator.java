/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.view.animation;

import android.view.animation.Interpolator;

public class QuarticEaseInOutInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float t) {
        float x = t * 2.0f;
        if (x < 1.0f) {
            return 0.5f * x * x * x * x;
        }
        x -= 2.0f;
        return (x * x * x * x - 2.0f) * -0.5f;
    }
}
