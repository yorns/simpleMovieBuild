# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=254d223b9e70204fcb33cd46be4df2d7"

SRC_URI = "git://github.com/yorns/simpleMovieUi.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
# PV="${SRCPV}"

S = "${WORKDIR}/git"

# NOTE: the following library dependencies are unknown, ignoring: snc_client
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = " snc libcec ncurses omxplayer boost bash"
RDEPENDS_${PN} += "bash udev-extraconf"
inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
# EXTRA_OECMAKE = ""

do_install_append() {
  install -d ${D}${systemd_unitdir}/system/systemd-udevd.service.d
  install -m 0644 ${S}/scripts/udev/myoverride.conf ${D}${systemd_unitdir}/system/systemd-udevd.service.d
  install -d ${D}${systemd_unitdir}/system
#  install -m 0644 ${S}/systemd/remote_handler.service ${D}${systemd_unitdir}/system
  install -d ${D}${systemd_unitdir}/system/getty@tty1.service.d
  install -m 0644 ${S}/scripts/tty/myoverride.conf ${D}${systemd_unitdir}/system/getty@tty1.service.d
  install -d ${D}/usr/share/consolefonts
  install -m 0644 ${S}/font/Lat15-TerminusBold32x16.psf.gz ${D}/usr/share/consolefonts
  install -d ${D}${sysconfdir}/udev/scripts
  install -m 0755 ${S}/scripts/mount.sh ${D}${sysconfdir}/udev/scripts/mount1.sh 
}

FILES_${PN} += "usr/share/consolefonts/Lat15-TerminusBold32x16.psf.gz \
		lib/systemd/system \               
		lib/system/getty@tty1.service.d/myoverride.conf \
		lib/system/systemd-udevd.service.d/myoverride.conf \
		lib/system/remote_handler.service \
                etc/udev/scripts/mount.sh \
               "
