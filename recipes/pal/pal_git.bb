inherit autotools pkgconfig

DESCRIPTION = "pal"
SECTION = "multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI  = "file://vendor/qcom/opensource/arpal-lx/"
SRC_URI += "file://${BASEMACHINE}/"

S = "${WORKDIR}/vendor/qcom/opensource/arpal-lx/"
PR = "r0"
DEPENDS = "media-headers tinyalsa tinycompress ar-gsl expat glib-2.0 capiv2-headers acdbdata audio-route agm-sw-binder-client spf ar-osal"

EXTRA_OECONF += "${@bb.utils.contains_any('BASEMACHINE', 'sdxlemur', 'IS_SDXLEMUR_TARGET=true', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains_any('BASEMACHINE', 'sa525m', 'IS_SA525M_TARGET=true', '', d)}"
EXTRA_OECONF += "--with-glib"
EXTRA_OECONF += "--with-spf=${STAGING_INCDIR}/spf"
EXTRA_OECONF += "--with-acdbdata=${STAGING_INCDIR}/acdbdata"

do_install:append() {
       install -d ${D}${sysconfdir}
       install -m 0755 ${WORKDIR}/${BASEMACHINE}/* ${D}${sysconfdir}/
}

do_install:append:sa525m(){
       install -d ${D}${sysconfdir}
       install -m 0664 ${WORKDIR}/${BASEMACHINE}/resourcemanager.xml ${D}${sysconfdir}/
       install -m 0660 ${WORKDIR}/${BASEMACHINE}/kvh2xml.xml ${D}${sysconfdir}/
       install -m 0660 ${WORKDIR}/${BASEMACHINE}/mixer_paths.xml ${D}${sysconfdir}/
       install -m 0440 ${WORKDIR}/${BASEMACHINE}/hw_ep_info.xml ${D}${sysconfdir}/
       
       chown root:audio ${D}${sysconfdir}/mixer_paths.xml
}

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
