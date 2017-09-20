#!/bin/bash

set -e

ENV=$1
DIRECTORY=$(pwd)

#Properties
# [TP]
# [TP]
# [TP]

MODE="embedded"
OSUSER="cissys"
PSRMPATH="/software/oracle/product/ouaf/psrmdev/"
PSRMENV="psrmdev"

#sudo chmod -R 775 "$DIRECTORY/Utilities"

sudo -u $OSUSER -i<<EOT
if [ "$MODE" != "native" ]; then
	cd "$PSRMPATH"'bin/';
    . ./splenviron.sh -e "$PSRMENV";
    spl.sh -a start
    echo "Waiting for PSRM to start up"
#    sleep 5m
#    spl.sh -m stop
else
	#echo "start script placeholder"
    cd "$DIRECTORY"
    "$DIRECTORY/Utilities/Scripts/WLST/startApplets.sh" "$ENV"
fi
EOT
