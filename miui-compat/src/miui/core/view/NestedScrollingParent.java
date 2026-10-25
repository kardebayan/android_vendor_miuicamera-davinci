/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.core.view;

import android.view.View;

public interface NestedScrollingParent {
    boolean onStartNestedScroll(View child, View target, int axes);

    void onNestedScrollAccepted(View child, View target, int axes);

    void onStopNestedScroll(View target);

    void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
            int dyUnconsumed);

    void onNestedPreScroll(View target, int dx, int dy, int[] consumed);

    boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed);

    boolean onNestedPreFling(View target, float velocityX, float velocityY);
}
