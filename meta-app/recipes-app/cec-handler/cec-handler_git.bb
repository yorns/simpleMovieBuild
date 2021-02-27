DESCRIPTION = "Remote Control Handler"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=254d223b9e70204fcb33cd46be4df2d7"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/systemd:"

SRC_URI = "file://cec-handler.service"

DEPENDS = "libcec \
  snc \
  "

inherit systemd

SYSTEMD_SERVICE_${PN} = "cec-handler.service"

SYSTEMD_AUTO_ENABLE_${PN} = "enable"

