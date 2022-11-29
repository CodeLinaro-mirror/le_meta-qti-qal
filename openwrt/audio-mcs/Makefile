include $(TOPDIR)/rules.mk

PKG_NAME:=audio-mcs
PKG_RELEASE:=1
PKG_FIXUP:=autoreconf

LOCAL_SRC ?= $(TOPDIR)/src/audio/mm-audio/audio_mcs/mcs/

include $(INCLUDE_DIR)/package.mk

define Package/audio-mcs
  SECTION:=AUDIO
  CATEGORY:=AUDIO
  MAINTAINER:=QTI MM Audio
  TITLE:=MCS
  DEPENDS:= +libcutils +glib2 +ar-osal +acdb +acdbdata +pal
endef

define Package/audio-mcs/description
  AUDIO MCS
endef

CONFIGURE_ARGS += \
	--with-openwrt \
	--with-glib

TARGET_CFLAGS += \
	-DFEATURE_IPQ_OPENWRT \
	-I$(STAGING_DIR)/usr/include/pal/ \
	-I$(STAGING_DIR)/usr/include/ar-osal/ \
	-I$(STAGING_DIR)/usr/include/glib-2.0/ \
	-I$(STAGING_DIR)/usr/include/acdb/ \
	-I$(STAGING_DIR)/usr/include/acdbdata/ \
	-I$(STAGING_DIR)/usr/include/ats/ \
	-I$(TOPDIR)/src/audio/mm-audio-headers/acdbdata/

TARGET_LDFLAGS += \
	-L$(STAGING_DIR)/usr/lib/

define Build/Prepare
mkdir -p $(PKG_BUILD_DIR)/
	$(CP) $(LOCAL_SRC)/* $(PKG_BUILD_DIR)/
endef

define Build/InstallDev
	$(INSTALL_DIR) $(STAGING_DIR)
	mkdir -p $(1)/usr/include/mcs/
	mkdir -p $(1)/usr/lib/
	$(CP) $(PKG_BUILD_DIR)/inc/*.h $(1)/usr/include/mcs/
	$(CP) $(PKG_BUILD_DIR)/.libs/libaudiomcs.so $(1)/usr/lib/
endef

define Package/audio-mcs/install
	$(INSTALL_DIR) $(1)/usr/lib/
	$(CP) $(PKG_BUILD_DIR)/.libs/libaudiomcs.so $(1)/usr/lib/
endef

$(eval $(call BuildPackage,audio-mcs))
