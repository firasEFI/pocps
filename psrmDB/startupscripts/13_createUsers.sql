alter session set "_ORACLE_SCRIPT"=true;
CREATE USER CISADM IDENTIFIED BY Icisk2016 DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP PROFILE DEFAULT;
GRANT UNLIMITED TABLESPACE TO CISADM WITH ADMIN OPTION;
GRANT SELECT ANY TABLE TO CISADM;
GRANT CREATE DATABASE LINK TO CISADM;
GRANT CONNECT TO CISADM;
GRANT RESOURCE TO CISADM;
GRANT DBA TO CISADM WITH ADMIN OPTION;
GRANT CREATE ANY SYNONYM TO CISADM;
GRANT SELECT ANY DICTIONARY TO CISADM;
CREATE USER CISUSER PROFILE DEFAULT IDENTIFIED BY CISUSER DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT SELECT ANY TABLE TO CISUSER;
GRANT CIS_USER TO CISUSER;
GRANT CIS_READ TO CISUSER;
GRANT CONNECT TO CISUSER;
CREATE USER CISOPR PROFILE DEFAULT IDENTIFIED BY OPRPLUS DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT CONNECT,RESOURCE,EXP_FULL_DATABASE TO CISOPR;
CREATE USER CISREAD IDENTIFIED BY CISREAD DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT SELECT ANY TABLE TO CISREAD;
GRANT CIS_READ TO CISREAD;
GRANT CONNECT TO CISREAD;

CREATE USER BATCH_ADMINISTRATION IDENTIFIED BY "netcompany-123" DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION TO BATCH_ADMINISTRATION;
GRANT CREATE TABLE TO BATCH_ADMINISTRATION;
GRANT CREATE VIEW TO BATCH_ADMINISTRATION;
GRANT CREATE ANY TRIGGER TO BATCH_ADMINISTRATION;
GRANT CREATE ANY PROCEDURE TO BATCH_ADMINISTRATION;
GRANT CREATE SEQUENCE TO BATCH_ADMINISTRATION;
GRANT CREATE SYNONYM TO BATCH_ADMINISTRATION;
ALTER USER BATCH_ADMINISTRATION quota unlimited on CISTS_01;

CREATE USER NYMFBATCH IDENTIFIED BY "netcompany-123" DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION TO NYMFBATCH;
GRANT CREATE TABLE TO NYMFBATCH;
GRANT CREATE VIEW TO NYMFBATCH;
GRANT CREATE ANY TRIGGER TO NYMFBATCH;
GRANT CREATE ANY PROCEDURE TO NYMFBATCH;
GRANT CREATE SEQUENCE TO NYMFBATCH;
GRANT CREATE SYNONYM TO NYMFBATCH;
ALTER USER NYMFBATCH quota unlimited on CISTS_01;

CREATE USER BATCH IDENTIFIED BY "netcompany-123" DEFAULT TABLESPACE CISTS_01 TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION TO BATCH;
GRANT CREATE TABLE TO BATCH;
GRANT CREATE VIEW TO BATCH;
GRANT CREATE ANY TRIGGER TO BATCH;
GRANT CREATE ANY PROCEDURE TO BATCH;
GRANT CREATE SEQUENCE TO BATCH;
GRANT CREATE SYNONYM TO BATCH;
ALTER USER BATCH quota unlimited on CISTS_01;

CREATE USER opa IDENTIFIED BY "Netcompany-321" DEFAULT TABLESPACE opa1 TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION TO opa;
GRANT CREATE TABLE TO opa;
GRANT CREATE VIEW TO opa;
GRANT CREATE ANY TRIGGER TO opa;
GRANT CREATE ANY PROCEDURE TO opa;
GRANT CREATE SEQUENCE TO opa;
GRANT CREATE SYNONYM TO opa;
ALTER USER opa quota unlimited on opa1;
