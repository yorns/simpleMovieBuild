# Install wpa_supplicant configuration file

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant.conf"

do_install_append() {
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

	# link instance-specific units---this is equivalent to calling
        #   $ systemctl enable wpa_supplicant@wlan0
        # on the target system.
#	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
#        ln -s ${systemd_unitdir}/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
}

# Configure location of config file in package
CONFFILES_${PN} += "${sysconfdir}/wpa_supplicant/*.conf"

SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE_${PN}_append = " wpa_supplicant-wlan0.conf"
