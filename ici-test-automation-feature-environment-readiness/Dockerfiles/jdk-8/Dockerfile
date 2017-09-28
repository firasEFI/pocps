# This Dockerfile is used to build an image containing basic stuff to be used as a Jenkins slave build node.
FROM maven:3-jdk-8
MAINTAINER Jakob Øjvind Nielsen <jakob.ojvind@testware.dk>

RUN apt-get update && apt-get install -y openssh-server
RUN mkdir /var/run/sshd
RUN echo 'root:screencast' | chpasswd
RUN sed -i 's/PermitRootLogin without-password/PermitRootLogin yes/g' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

# SSH login fix
RUN echo PermitRootLogin yes                        >> /etc/ssh/sshd_config
RUN echo PasswordAuthentication yes                 >> /etc/ssh/sshd_config
RUN echo AuthorizedKeysFile %h/.ssh/authorized_keys >> /etc/ssh/sshd_config

EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]