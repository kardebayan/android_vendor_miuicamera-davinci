#
# Automatically generated file. DO NOT MODIFY
#

PRODUCT_SOONG_NAMESPACES += \
    vendor/miuicamera-davinci/xiaomi

PRODUCT_COPY_FILES += \
    vendor/miuicamera-davinci/xiaomi/proprietary/vendor/etc/camera/visidon_clearshot_params_algoup.xml:$(TARGET_COPY_OUT_VENDOR)/etc/camera/visidon_clearshot_params_algoup.xml \
    vendor/miuicamera-davinci/xiaomi/proprietary/vendor/etc/screen_light.xml:$(TARGET_COPY_OUT_VENDOR)/etc/screen_light.xml

PRODUCT_PACKAGES += \
    libcamera_algoup_jni.xiaomi \
    libcamera_mianode_jni.xiaomi \
    libmialgoengine \
    MiuiCamera

PRODUCT_PACKAGES += \
    system_priv-app_MiuiCamera_lib_arm64_libcamera_algoup_jni_xiaomi_so \
    system_priv-app_MiuiCamera_lib_arm64_libcamera_mianode_jni_xiaomi_so
