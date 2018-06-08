DESCRIPTION = "Control Sender"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=254d223b9e70204fcb33cd46be4df2d7"

SRC_URI = "git://github.com/yorns/controlSender.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "boost \
  snc \
  dropbear \
  "

inherit cmake
