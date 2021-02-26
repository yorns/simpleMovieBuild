include recipes-core/images/rpi-hwup-image.bb

DESCRIPTION = "Custom image based on rpi-basic-image"

# We only need a rpi-sdimg image here
IMAGE_FSTYPES_raspberrypi3 ?= "tar.bz2 rpi-sdimg"

IMAGE_FEATURES += "ssh-server-dropbear"

# Additional packages
IMAGE_INSTALL_append = " \
  wpa-supplicant \
  linux-firmware-bcm43430 \
  snc \
  simplemovieui \
  libcec \
  bash \
  nfs-utils \
  "

export IMAGE_BASENAME = "rpi-image"
