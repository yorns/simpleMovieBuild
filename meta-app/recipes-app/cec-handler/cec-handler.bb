DESCRIPTION = "Remote Control Handler"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=254d223b9e70204fcb33cd46be4df2d7"

S = "${WORKDIR}"

RDEPENDS = "libcec snc " 

inherit systemd

SYSTEMD_SERVICE_${PN} = "cec-handler.service"

do_install_append() {
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${S}/cec-handler.service ${D}${systemd_unitdir}/system
}

SYSTEMD_AUTO_ENABLE_${PN} = "enable"

