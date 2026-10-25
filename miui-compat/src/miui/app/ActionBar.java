/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.app;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

/**
 * MIUI action bar with fragment view pager tabs. miui.app.Activity never hands one out,
 * callers check for null.
 */
public abstract class ActionBar extends android.app.ActionBar {
    public interface FragmentViewPagerChangeListener {
        void onPageScrollStateChanged(int state);

        void onPageScrolled(int position, float offset, boolean fromHasActionMenu,
                boolean toHasActionMenu);

        void onPageSelected(int position);
    }

    public abstract int addFragmentTab(String tag, android.app.ActionBar.Tab tab,
            Class<?> fragmentClass, Bundle args, boolean hasActionMenu);

    public abstract void addOnFragmentViewPagerChangeListener(
            FragmentViewPagerChangeListener listener);

    public abstract void setFragmentViewPagerMode(Context context, FragmentManager manager,
            boolean allowSwipe);
}
