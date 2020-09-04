inherit autotools pkgconfig

DESCRIPTION = "mcs"
SECTION = "multimedia"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=3775480a712fc46a69647678acb234cb"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI  = "file://audio/mm-audio/audio_mcs/mcs/"
S = "${WORKDIR}/audio/mm-audio/audio_mcs/mcs/"

EXTRA_OECONF += "--with-acdbdata=${STAGING_INCDIR}/acdbdata"

DEPENDS = "ar-osal qal glib-2.0"
PR = "r0"

EXTRA_OECONF += "--with-glib"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
