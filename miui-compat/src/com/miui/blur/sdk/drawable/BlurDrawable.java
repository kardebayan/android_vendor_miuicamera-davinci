/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.miui.blur.sdk.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/** MIUI window blur. Reports no support so miuix falls back to a plain background. */
public class BlurDrawable extends Drawable {
    public boolean isSupportBlur() {
        return false;
    }

    public void setBlurRatio(float ratio) {}

    public void setMixColor(int mode, int color) {}

    @Override
    public void draw(Canvas canvas) {}

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter colorFilter) {}

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
