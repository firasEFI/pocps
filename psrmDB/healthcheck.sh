#!/bin/bash
export ORACLE_HOME=/opt/oracle/product/12.1.0.2/dbhome_1

CHECK=$(/opt/oracle/product/12.1.0.2/dbhome_1/bin/sqlplus CISADM/Icisk2016@localhost:1521/ORCLCDB <<END
  set pagesize 0 feedback off verify off heading off echo off;
  select count(*) from ALL_TABLES where TABLE_NAME = '${HEALTHCHECK_TABLE}';
  exit;
END
)

# Number check
if ! [ "${CHECK}" -eq "${CHECK}" ] 2>/dev/null; then
  echo ${CHECK}
  exit 1
fi

# Exist table check
if [ ${CHECK} -gt 0 ]; then
  echo "Healthy"
  exit 0
else
  exit 1
fi
