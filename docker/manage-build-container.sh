#!/bin/bash

docker_image_name="yocto-bs"
docker_image_tag="ubuntu-16.04"

# Create volume with:
# docker volume create buildvol
build_volume="buildvol"

build_docker_image() {
  docker build . -t ${docker_image_name}:${docker_image_tag}
}

run_docker_container() {
  if [[ "$build_volume" = "/*" ]] && [ ! -d $build_volume ]; then
    echo "ERROR: Path $build_volume not found or not readable"
    exit 1
  fi

  docker run --rm -it \
    -v $build_volume:/workdir \
    -e USERID=$(id -u) \
    -e GROUPID=$(id -g) \
    -e LANG=en_US.UTF-8 \
    -e LANGUAGE=en_US:en \
    -e LC_ALL=en_US.UTF-8 \
    --workdir /workdir \
    --name yocto-build \
    ${docker_image_name}:${docker_image_tag} \
    /bin/bash
}

if [ "$1" = "build" ]; then
  build_docker_image
elif [ "$1" = "run" ]; then
  run_docker_container
else
  echo "Usage: $0 (build|run)"
  exit 1
fi
