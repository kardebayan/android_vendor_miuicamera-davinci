/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.core.view;

import android.view.View;

public interface NestedScrollingParent2 extends NestedScrollingParent {
    boolean onStartNestedScroll(View child, View target, int axes, int type);

    void onNestedScrollAccepted(View child, View target, int axes, int type);

    void onStopNestedScroll(View target, int type);

    void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
            int dyUnconsumed, int type);

    void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type);
}
