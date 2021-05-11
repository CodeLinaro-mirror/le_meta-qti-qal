inherit autotools pkgconfig

DESCRIPTION = "pal"
SECTION = "multimedia"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI  = "file://vendor/qcom/opensource/arpal-lx/"
SRC_URI += "file://${BASEMACHINE}/"

S = "${WORKDIR}/vendor/qcom/opensource/arpal-lx/"
PR = "r0"
DEPENDS = "media-headers tinyalsa tinycompress ar-gsl expat glib-2.0 capiv2-headers acdbdata audio-route agm "

EXTRA_OECONF += "--with-glib"
EXTRA_OECONF += "--with-gecko=${STAGING_INCDIR}/gecko"
EXTRA_OECONF += "--with-acdbdata=${STAGING_INCDIR}/acdbdata"

do_install_append() {
       install -d ${D}${sysconfdir}
       install -m 0755 ${WORKDIR}/${BASEMACHINE}/* ${D}${sysconfdir}/
}

SOLIBS = ".so"

FILES_SOLIBSDEV = ""
