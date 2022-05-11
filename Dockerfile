FROM openjdk:17-slim AS builder

WORKDIR /app

# download most dependencies
# exclude generateGitProperties -- .git folder is not copied to allow caching
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle/ gradle/
RUN ./gradlew classes --exclude-task=generateGitProperties

# compile main app
# exclude generateGitProperties -- .git folder is not copied to allow caching
COPY src/main/ src/main/
RUN ./gradlew classes --exclude-task=generateGitProperties

# assemble extracts information from .git and BUILD_NUMBER env var, these layers change for all commits
ARG BUILD_NUMBER
ENV BUILD_NUMBER ${BUILD_NUMBER:-1_0_0}
COPY . .
RUN ./gradlew assemble


# ---
FROM alpine:3.15 AS final
LABEL maintainer="HMPPS Digital Studio <info@digital.justice.gov.uk>"

# force a rebuild of `apk upgrade` below by invalidating the BUILD_NUMBER env variable on every commit
ARG BUILD_NUMBER
ENV BUILD_NUMBER ${BUILD_NUMBER:-1_0_0}

RUN apk upgrade --no-cache && \
     apk add --no-cache \
       curl \
       openjdk17-jre-headless \
       tzdata

ENV TZ=Europe/London
RUN ln -snf "/usr/share/zoneinfo/$TZ" /etc/localtime && echo "$TZ" > /etc/timezone

RUN addgroup --gid 2000 --system appgroup && \
    adduser --uid 2000 --system appuser && \
    adduser appuser appgroup

WORKDIR /app
COPY --from=builder --chown=appuser:appgroup /app/build/libs/hmpps-interventions-onboarding*.jar /app/app.jar
COPY --from=builder --chown=appuser:appgroup /app/build/libs/applicationinsights-agent*.jar /app/agent.jar
COPY --chown=appuser:appgroup applicationinsights.json /app
COPY --chown=appuser:appgroup applicationinsights.dev.json /app

USER 2000

ENTRYPOINT ["java", "-XX:+AlwaysActAsServerClassMachine", "-javaagent:/app/agent.jar", "-jar", "/app/app.jar"]
