/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.core.view;

import android.view.View;

public interface NestedScrollingParent3 extends NestedScrollingParent2 {
    void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
            int dyUnconsumed, int type, int[] consumed);
}
