/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui;

/** Resources of the MIUI SDK (com.miui.core), mapped to placeholders or AOSP equivalents. */
public final class R {
    public static final class attr {
        // Only used as drawable state markers, any distinct values work
        public static final int state_first_v = 0x7e000001;
        public static final int state_middle_v = 0x7e000002;
        public static final int state_last_v = 0x7e000003;
        public static final int state_single_v = 0x7e000004;
    }

    public static final class style {
        public static final int Widget_Button_Dialog =
                android.R.style.Widget_DeviceDefault_Button_Borderless_Colored;
    }
}
