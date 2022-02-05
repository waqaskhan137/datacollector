FROM openjdk:19-jdk-alpine as base

WORKDIR /downloads

RUN wget -O apache-maven-3.6.3-bin.tar.gz https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN mkdir -p /usr/maven/ && mv ./apache-maven-3.6.3-bin.tar.gz /usr/maven/ && cd /usr/maven/ && tar -xzvf apache-maven-3.6.3-bin.tar.gz

RUN ln -s /usr/maven/apache-maven-3.6.3/bin/mvn /usr/bin/mvn
RUN rm -rf /usr/maven/apache-maven-3.6.3-bin.tar.gz

RUN apk add nano

ENV MAVEN_HOME /usr/maven/apache-maven-3.6.3

WORKDIR /build

COPY pom.xml .
RUN mvn clean package -Dmaven.main.skip -Dmaven.test.skip && rm -r target

COPY src/ /build/src/
RUN mvn clean package -Dmaven.test.skip


RUN cp /build/target/datacollector-0.1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]