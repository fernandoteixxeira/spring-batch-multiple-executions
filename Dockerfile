FROM openjdk:11

USER root

RUN apt-get -y update && \
    apt-get -y autoremove && \
    apt-get -y autoclean && \
    apt-get -y install wget && \
    apt-get -y install unzip && \
    apt-get -y clean

RUN mkdir /app && \
    groupadd app && \
    useradd app -u 1001 -g app -d /app -s /bin/bash && \
    chown 1001:app /app -R

RUN wget "https://services.gradle.org/distributions/gradle-5.4.1-bin.zip" -O gradle-5.4.1-bin.zip && \
    unzip -d /opt/gradle gradle-5.4.1-bin.zip

ENV PATH $PATH:/opt/gradle/gradle-5.4.1/bin

# PostgreSQL
ARG DATASOURCE_DRIVE
ENV DATASOURCE_DRIVE $DATASOURCE_DRIVE
ARG DATASOURCE_URL
ENV DATASOURCE_URL $DATASOURCE_URL
ARG DATASOURCE_USERNAME
ENV DATASOURCE_USERNAME $DATASOURCE_USERNAME
ARG DATASOURCE_PASSWORD
ENV DATASOURCE_PASSWORD $DATASOURCE_PASSWORD

# General
ARG SPRING_ACTIVE_PROFILE
ENV SPRING_ACTIVE_PROFILE $SPRING_ACTIVE_PROFILE

# Batch
ARG BATCH_JOB_NAME
ENV BATCH_JOB_NAME $BATCH_JOB_NAME

COPY . /app
WORKDIR /app

RUN gradle build

EXPOSE 8010

ENTRYPOINT java -DjobName=$BATCH_JOB_NAME -jar /app/build/libs/spring-batch-multiple-executions-*.jar