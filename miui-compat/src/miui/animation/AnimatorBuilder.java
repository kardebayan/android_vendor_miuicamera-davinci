/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;

public class AnimatorBuilder {
    private final Animator mAnimator;

    private AnimatorBuilder(Animator animator) {
        mAnimator = animator;
    }

    public static AnimatorBuilder of(Animator animator) {
        return new AnimatorBuilder(animator);
    }

    public AnimatorBuilder setDuration(long duration) {
        mAnimator.setDuration(duration);
        return this;
    }

    public AnimatorBuilder setInterpolator(TimeInterpolator interpolator) {
        mAnimator.setInterpolator(interpolator);
        return this;
    }

    public AnimatorBuilder addListener(Animator.AnimatorListener listener) {
        mAnimator.addListener(listener);
        return this;
    }

    public Animator animator() {
        return mAnimator;
    }
}
