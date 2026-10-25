#!/usr/bin/env -S PYTHONPATH=../../tools/extract-utils python3
#
# SPDX-FileCopyrightText: 2024-2026 The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

import tempfile
from os import path

from extract_utils.fixups_blob import (
    blob_fixup,
    blob_fixups_user_type,
)
from extract_utils.fixups_lib import (
    lib_fixups,
    lib_fixups_user_type,
)
from extract_utils.main import (
    ExtractUtils,
    ExtractUtilsModule,
)
from extract_utils.tools import (
    android_root,
    apktool_path,
    java_path,
)
from extract_utils.utils import run_cmd

namespace_imports = [
    'hardware/qcom-caf/sm8150',
    'vendor/qcom/opensource/display',
    'vendor/xiaomi/davinci',
    'vendor/xiaomi/sm6150-common',
]

aapt2_path = path.join(android_root, 'prebuilts/sdk/tools/linux/bin/aapt2')


def blob_fixup_apktool_unpack_miui(
    ctx,
    file,
    file_path,
    *args,
    tmp_dir=None,
    **kwargs,
):
    # MiuiCamera references resources of the MIUI SDK (com.miui.core,
    # package ID 0x10), which apktool needs to decode it. Build a stub
    # framework with just the referenced entries, the patches drop them all.
    stub_dir = path.join(ctx.module_dir, 'miui-res-stub')
    with tempfile.TemporaryDirectory() as frame_dir:
        compiled_res = path.join(frame_dir, 'res.zip')
        stub_apk = path.join(frame_dir, 'miui.apk')
        run_cmd(
            [
                aapt2_path,
                'compile',
                '--dir',
                path.join(stub_dir, 'res'),
                '-o',
                compiled_res,
            ]
        )
        run_cmd(
            [
                aapt2_path,
                'link',
                '-o',
                stub_apk,
                '--manifest',
                path.join(stub_dir, 'AndroidManifest.xml'),
                '--package-id',
                '0x10',
                '--allow-reserved-package-id',
                '--rename-resources-package',
                'miui',
                compiled_res,
            ]
        )
        run_cmd([java_path, '-jar', apktool_path, 'if', '-p', frame_dir, stub_apk])
        run_cmd(
            [
                java_path,
                '-jar',
                apktool_path,
                'd',
                file_path,
                '-o',
                tmp_dir,
                '-f',
                '-p',
                frame_dir,
            ]
        )


lib_fixups: lib_fixups_user_type = {
    **lib_fixups,
}

blob_fixups: blob_fixups_user_type = {
    # Replace the R-era Surface vtable connect() call with ANativeWindow perform(NATIVE_WINDOW_API_CONNECT, NATIVE_WINDOW_API_CPU)
    ('system/lib64/libcamera_algoup_jni.xiaomi.so', 'system/lib64/libcamera_mianode_jni.xiaomi.so'): blob_fixup()
        .sig_replace('68 02 40 f9 e2 03 00 91 41 00 80 52 e0 03 13 aa 08 9d 40 f9 e3 03 1f 2a 00 01 3f d6', '60 42 00 91 08 4c 40 f9 a1 01 80 52 42 00 80 52 00 01 3f d6 1f 20 03 d5 1f 20 03 d5'),
    'system/priv-app/MiuiCamera/MiuiCamera.apk': blob_fixup()
        .call(blob_fixup_apktool_unpack_miui)
        .patch_dir('patches')
        .apktool_pack()
        .stripzip(),
}  # fmt: skip

module = ExtractUtilsModule(
    'xiaomi',
    'miuicamera-davinci',
    device_rel_path='vendor/miuicamera-davinci',
    blob_fixups=blob_fixups,
    lib_fixups=lib_fixups,
    namespace_imports=namespace_imports,
)

if __name__ == '__main__':
    utils = ExtractUtils.device(module)
    utils.run()
