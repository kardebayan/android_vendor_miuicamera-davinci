/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.view.animation;

import android.view.animation.Interpolator;

public class QuarticEaseInInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float t) {
        return t * t * t * t;
    }
}
