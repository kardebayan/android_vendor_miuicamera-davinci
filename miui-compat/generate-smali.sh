#!/bin/bash
#
# SPDX-FileCopyrightText: 2026 The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#
# Compiles the MIUI SDK compatibility classes and disassembles them into
# smali_classes4/ of a decoded MiuiCamera.apk, using the same apktool as
# extract-utils. Regenerate patches/0006-Add-MIUI-SDK-compatibility-classes.patch
# from the result after changes.
#
# Usage: generate-smali.sh <decoded MiuiCamera.apk directory>

set -e

if [ -z "$1" ]; then
    echo "Usage: $0 <decoded MiuiCamera.apk directory>"
    exit 1
fi

MY_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ANDROID_ROOT="$(cd "${MY_DIR}/../../.." && pwd)"
OUT_DIR="$(cd "$1" && pwd)"

JDK="${ANDROID_ROOT}/prebuilts/jdk/jdk21/linux-x86/bin"
R8="${ANDROID_ROOT}/prebuilts/r8/r8.jar"
APKTOOL="${ANDROID_ROOT}/prebuilts/extract-tools/common/apktool/apktool.jar"
ANDROID_JAR="${ANDROID_ROOT}/prebuilts/sdk/current/system/android.jar"

TMP_DIR="$(mktemp -d)"
trap 'rm -rf "${TMP_DIR}"' EXIT

mkdir -p "${TMP_DIR}/classes" "${TMP_DIR}/dex"
"${JDK}/javac" -source 8 -target 8 -nowarn -Xlint:-options \
    -bootclasspath "${ANDROID_JAR}" \
    -d "${TMP_DIR}/classes" \
    $(find "${MY_DIR}/src" -name '*.java')

"${JDK}/java" -cp "${R8}" com.android.tools.r8.D8 --release --min-api 28 \
    --lib "${ANDROID_JAR}" \
    --output "${TMP_DIR}/dex" \
    $(find "${TMP_DIR}/classes" -name '*.class')

(cd "${TMP_DIR}/dex" && zip -q "${TMP_DIR}/compat.apk" classes.dex)
"${JDK}/java" -jar "${APKTOOL}" d -q -r -f "${TMP_DIR}/compat.apk" -o "${TMP_DIR}/compat"

rm -rf "${OUT_DIR}/smali_classes4"
mv "${TMP_DIR}/compat/smali" "${OUT_DIR}/smali_classes4"
