FROM skmici/serverbase:latest

MAINTAINER Peer Jakobsen <peer.jakobsen@eficode.com>

ENV FMW_PKG=fmw_12.1.3.0.0_wls.zip \
    FMW_JAR=fmw_12.1.3.0.0_wls.jar \
    JAVA_HOME=/usr/java/default \
    ORACLE_BASE=/software/oracle \
    ORACLE_HOME=/software/oracle/product/weblogic/12.1.3.0.0 \
    USER_MEM_ARGS="-Djava.security.egd=file:/dev/./urandom" \
    PATH=$PATH:/usr/java/default/bin:/software/oracle/product/weblogic/12.1.3.0.0/oracle_common/common/bin:/software/oracle/product/weblogic/12.1.3.0.0/wlserver/common/bin

ADD binaries/weblogic/$FMW_PKG binaries/weblogic/install.file binaries/weblogic/oraInst.loc binaries/weblogic/weblogic_install.rsp /tmp/

RUN mkdir -p $ORACLE_HOME/ && chown -R cissys:cisusr /software/oracle/product/weblogic && \
    chown cissys:cisusr /tmp/install.file /tmp/oraInst.loc /tmp/weblogic_install.rsp

# unzip weblogic
RUN unzip -oq /tmp/$FMW_PKG -d /tmp/extracted
RUN chown -R cissys:cisusr /tmp/extracted/$FMW_JAR

USER cissys
RUN $JAVA_HOME/bin/java -D64 -jar /tmp/extracted/$FMW_JAR -silent -ignoreSysPrereqs -responseFile /tmp/weblogic_install.rsp -invPtrLoc /tmp/oraInst.loc ORACLE_HOME=$ORACLE_HOME

USER root
# cleaning up
RUN rm -rf /tmp/install.file /tmp/oraInst.loc /tmp/extracted/$FMW_JAR /tmp/weblogic_install.rsp /tmp/$FMW_PKG

# create psrmdev ouaf
COPY binaries/psrmdev/cistab /etc/

RUN mkdir -p /software/oracle/product/ouaf/psrmdev && chown -R cissys:cisusr /software/oracle/product/ouaf && \
    mkdir -p /data/logs/ouaf/psrmdev && chown -R cissys:cisusr /data/logs/ouaf

USER cissys
ADD binaries/psrmdev/psrmdev.tar.gz /

USER root
ADD binaries/opa/opa.zip /tmp/
# Unzip OPA
RUN unzip -oq /tmp/opa.zip -d /tmp/extracted && \
    chown -R cissys:cisusr /tmp/extracted/opa && \
    rm /tmp/opa.zip


RUN yum update -y && yum install -y zsh sudo zip initscripts
RUN yum clean all

RUN cd /tmp && curl -s get.sdkman.io | bash
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install groovy

COPY sudoers /etc/

COPY binaries/UnlimitedJCEPolicyJDK8/local_policy.jar binaries/UnlimitedJCEPolicyJDK8/US_export_policy.jar /usr/java/jdk1.8.0_144/jre/lib/security/

RUN usermod -a -G cissys oracle
#USER cissys
WORKDIR /tmp/release

ADD StartPSRM.sh /usr/bin/
RUN chmod +x /usr/bin/StartPSRM.sh
CMD /usr/bin/StartPSRM.sh DOCKER 7068 '' '' ''; sleep infinity

EXPOSE 6500 6501 6502 6503 6104 7101
