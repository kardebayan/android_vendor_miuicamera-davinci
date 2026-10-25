/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package android.os.statistics;

/** MIUI performance tracing, a no-op on AOSP. */
public class E2EScenarioPerfTracer {
    public static void asyncBeginScenario(E2EScenario scenario, E2EScenarioSettings settings,
            String tag, E2EScenarioPayload payload) {}

    public static void abortScenario(E2EScenario scenario, String tag) {}

    public static void finishScenario(E2EScenario scenario) {}

    public static void finishScenario(E2EScenario scenario, String tag,
            E2EScenarioPayload payload) {}
}
