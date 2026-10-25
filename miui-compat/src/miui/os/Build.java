/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.os;

import android.os.SystemProperties;
import android.text.TextUtils;

/** Mirrors the fields MIUI derives from build properties. */
public class Build extends android.os.Build {
    public static final boolean IS_ALPHA_BUILD =
            SystemProperties.get("ro.product.mod_device", "").endsWith("_alpha");
    public static final boolean IS_CM_CUSTOMIZATION =
            "cm".equals(SystemProperties.get("ro.carrier.name"))
                    && ("cn_chinamobile".equals(SystemProperties.get("ro.miui.cust_variant"))
                            || "cn_cta".equals(SystemProperties.get("ro.miui.cust_variant")));
    public static final boolean IS_CM_CUSTOMIZATION_TEST =
            "cm".equals(SystemProperties.get("ro.cust.test"));
    public static final boolean IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
    public static final boolean IS_INTERNATIONAL_BUILD =
            SystemProperties.get("ro.product.mod_device", "").contains("_global");
    public static final boolean IS_STABLE_VERSION = "user".equals(TYPE)
            && !TextUtils.isEmpty(VERSION.INCREMENTAL)
            && VERSION.INCREMENTAL.matches("^V(\\d+.)+([A-Z]+\\d{0,}.?)+(\\d+.?){0,}$");

    public static String getRegion() {
        return SystemProperties.get("ro.miui.region", "CN");
    }
}
