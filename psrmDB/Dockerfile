FROM skmici/dbprebuilt:12.1.0.2-ee

MAINTAINER Peer Jakobsen <peer.jakobsen@eficode.com>

ENV JAVA_PKG=jdk-8u*-linux-x64.tar.gz
ENV NLS_LANG=danish_denmark.al32utf8

USER root

# install groovy using sdkman (which requires zsh)
RUN yum update -y && yum install -y zsh
RUN yum clean all

RUN cd /tmp && curl -s get.sdkman.io | bash
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install groovy

#ADD archive.zip /tmp/release/
#RUN unzip -oq /tmp/release/archive.zip -d /tmp/release && rm -rf /tmp/release/archive.zip

ADD binaries/sqlcl/sqlcl-17.2.0.184.1230-no-jre.zip /tmp/
RUN unzip -oq /tmp/sqlcl-17.2.0.184.1230-no-jre.zip -d /opt/oracle/product && rm -rf /tmp/sqlcl-17.2.0.184.1230-no-jre.zip

# Add JAVA 8 and make sure sqlcl uses it
ADD binaries/java/$JAVA_PKG /opt/oracle/product/java/
RUN export JAVA_DIR=$(ls -1 -d /opt/oracle/product/java/jdk*) && \
    ln -s $JAVA_DIR /opt/oracle/product/sqlcl/jre


ENV JAVA_HOME=/opt/oracle/product/java/jdk1.8.0_144 \
    GROOVY_HOME=/root/.sdkman/candidates/groovy/current \
    PATH=$JAVA_HOME/bin:$GROOVY_HOME/bin:$PATH

# jdbc install
COPY binaries/java/ojdbc6-12.1.0.2-g.jar $JAVA_DIR/lib/

# Fix permission denied bug on oracle user
COPY oracle-rdbms-server-12cR1-preinstall.conf /etc/security/limits.d/

# oracle user should have db binaries in PATH
RUN echo 'export ORACLE_HOME=/opt/oracle/product/12.1.0.2/dbhome_1' >> /home/oracle/.bashrc && \
    echo 'export PATH=$ORACLE_HOME/bin:$ORACLE_HOME/perl/bin:$ORACLE_HOME/perl/lib:/opt/oracle/product/sqlcl/bin:$PATH' >> /home/oracle/.bashrc && \
    echo 'export NLS_LANG=danish_denmark.al32utf8' >> /home/oracle/.bashrc
#Copy sudoers
COPY sudoers /etc/

#COPY startupscripts/ /opt/oracle/scripts/startup/

USER oracle
WORKDIR /home/oracle
ENV NLS_LANG=danish_denmark.al32utf8

EXPOSE 1521 5500 5501 5502
