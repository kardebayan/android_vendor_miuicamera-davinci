/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.miui.internal.log.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/** Receiver for MIUI bug report log dumps, which never arrive on AOSP. */
public class DumpReceiver extends BroadcastReceiver {
    public DumpReceiver(String logDir, String tag) {}

    @Override
    public void onReceive(Context context, Intent intent) {}
}
