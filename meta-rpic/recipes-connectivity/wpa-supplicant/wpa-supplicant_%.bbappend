# Install wpa_supplicant configuration file

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant.conf file://wpa_supplicant.service"

do_install_append() {
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/wpa_supplicant.service ${D}/${systemd_unitdir}/system
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant.conf.skeleton
	rm -f ${D}${sysconfdir}/wpa_supplicant.conf
        rm -f ${D}/${systemd_unitdir}/system/wpa_supplicant-nl80211@.service
        rm -f ${D}/${systemd_unitdir}/system/wpa_supplicant-wired@.service
}

# Configure location of config file in package
CONFFILES_${PN} += "${sysconfdir}/wpa_supplicant/*.conf"

SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE_${PN}_append = " wpa_supplicant-wlan0.conf"
