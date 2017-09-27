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

#Wait until PSRM can reach the database
cond=NOK
while [ $cond != OK ]
do
  if echo "exit" | sqlplus CISADM/Icisk2016@orasqlserver:1521/ORCLCDB | grep Connected > /dev/null
     then
       cond=OK;
   fi

  sleep 10

done

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
