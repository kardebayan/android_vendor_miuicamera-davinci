/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.view.animation;

import android.view.animation.Interpolator;

public class SineEaseInOutInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float t) {
        return (float) (Math.cos(t * Math.PI) - 1.0d) * -0.5f;
    }
}
