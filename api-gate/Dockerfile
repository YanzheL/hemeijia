FROM maven:3-jdk-14 AS builder

WORKDIR /usr/src/app

COPY . .

RUN mvn package -B -DskipTests -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn \
    && cd target \
    && java -jar *.jar --thin.root=m2 --thin.dryrun \
    && java -jar *.jar --thin.classpath=properties > thin.properties

FROM openjdk:11-jre-slim

LABEL maintainer="Yanzhe Lee"

WORKDIR /usr/src/app/

COPY --from=builder /usr/src/app/target/*.jar ./app.jar

COPY --from=builder /usr/src/app/target/m2 ./m2

COPY --from=builder /usr/src/app/target/thin.properties ./thin.properties

CMD ['java', '-server', '-XX:+HeapDumpOnOutOfMemoryError', '-Xmx500m', '-XX:+UnlockExperimentalVMOptions', '-XX:+UseZGC', '-Dspring.profiles.active=test', '-Djava.security.egd=file:/dev/./urandom', '-jar', 'app.jar', '--thin.root=/usr/src/app/m2']
#CMD [ "java", "-server", "-XX:+HeapDumpOnOutOfMemoryError", "-Xmx500m", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseZGC", "-Dspring.profiles.active=test", "-jar", "app.jar" ]
