#!/bin/bash
set -e

username="build"

# If a USERID is specified change the uid
# of user "$username" to USERID and su to that user
# see: https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/#entrypoint
if [ ! -z "$USERID" ]; then
    usermod -u $USERID ${username}
fi

chown ${username}:${username} /workdir

su ${username}

exec "$@"
