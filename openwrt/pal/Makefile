include $(TOPDIR)/rules.mk

PKG_NAME:=pal
PKG_RELEASE:=1
PKG_FIXUP:=autoreconf

LOCAL_SRC ?= $(TOPDIR)/src/vendor/qcom/opensource/arpal-lx/

include $(INCLUDE_DIR)/package.mk

define Package/pal
  SECTION:=AUDIO
  CATEGORY:=AUDIO
  MAINTAINER:=QTI MM Audio
  TITLE:=PAL
  DEPENDS:= +media-headers +tinyalsa +tinycompress +ar-gsl +libexpat +glib2 +capiv2-headers +acdbdata +audio-route +agm +core-include
endef

define Package/pal/description
	PAL
endef

CONFIGURE_ARGS += \
	--with-openwrt \
	--with-glib

TARGET_CFLAGS += \
	-DFEATURE_IPQ_OPENWRT \
	-I$(STAGING_DIR)/usr/include/ar-osal \
	-I$(STAGING_DIR)/usr/include/ar-gsl/api \
	-I$(STAGING_DIR)/usr/include/ar_gsl/inc \
	-I$(STAGING_DIR)/usr/include/spf/api/modules/ \
	-I$(STAGING_DIR)/usr/include/spf/api/apm/ \
	-I$(STAGING_DIR)/usr/include/spf/api/ar_utils/ \
	-I$(STAGING_DIR)/usr/include/spf/api/vcpm/ \
	-I$(STAGING_DIR)/usr/include/agm \
	-I$(TOPDIR)/src/audio/mm-audio-headers/acdbdata/ \
	-I$(TOPDIR)/src/frameworks/audio_route/include/ \
	-I$(LOCAL_SRC)/plugins/codecs/ \
	-I$(STAGING_DIR)/usr/include/audio_route/ \
	-I$(STAGING_DIR)/usr/include/glib-2.0/ \
	-I$(STAGING_DIR)/usr/include/capiv2_headers/ \
	-I$(STAGING_DIR)/usr/include/system/audio_android_q/

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)/
	$(CP) $(LOCAL_SRC)/* $(PKG_BUILD_DIR)/
endef

define Build/InstallDev
	$(INSTALL_DIR) $(STAGING_DIR)
	mkdir -p $(1)/usr/include/pal/
	mkdir -p $(1)/usr/lib
	mkdir -p $(1)/etc
	$(CP) $(PKG_BUILD_DIR)/*.h $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/device/inc/ $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/resource_manager/inc/ $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/session/inc/ $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/stream/inc/ $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/utils/inc/ $(1)/usr/include/pal/
	$(CP) $(PKG_BUILD_DIR)/.libs/libpal.so* $(1)/usr/lib/
	$(CP) ./files/sdxpinn/* $(1)/etc
endef

define Package/pal/install
	$(INSTALL_DIR) $(1)/usr/lib
	$(INSTALL_DIR) $(1)/etc
	$(CP) $(PKG_BUILD_DIR)/.libs/libpal.so* $(1)/usr/lib
	$(CP) ./files/sdxpinn/* $(1)/etc
endef

$(eval $(call BuildPackage,pal))
