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
#Wait until PSRM can reach the database

sudo -u $OSUSER -i<<EOT
#Wait until PSRM can reach the database
while  /software/oracle/product/oracle_client/12.1.0.2/bin/sqlplus CISADM/Icisk2016@orasqlserver:1521/ORCLCDB | grep Connected; do sleep 10; done

EOT

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
