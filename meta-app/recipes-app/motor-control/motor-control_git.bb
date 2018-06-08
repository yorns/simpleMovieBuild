DESCRIPTION = "Motor Control"

# TODO: add proper license/license file
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=254d223b9e70204fcb33cd46be4df2d7"

SRC_URI = "git://github.com/yorns/motorControl.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

# Add dependency to wiringpi when building for the
# real target (not qemu)
WIRINGPIDEPS = "wiringpi"
WIRINGPIDEPS_qemuarm = ""

DEPENDS = "boost \
  snc \
  ${WIRINGPIDEPS} \
  "

inherit cmake
inherit systemd

SYSTEMD_SERVICE_${PN} = "motorControl.service"

do_install_append() {
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${S}/systemd/motorControl.service ${D}${systemd_unitdir}/system
}
