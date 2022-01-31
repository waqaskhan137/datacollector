# getting linux image
FROM alpine
WORKDIR /opt/
COPY classes/artifacts/sensorcall_jar/sensorcall.jar /opt/sensorcall.jar
COPY config.json /opt/config.json
CMD ["java","-jar","/opt/sensorcall.jar"]

#install JDK
RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin