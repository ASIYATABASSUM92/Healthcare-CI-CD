# DOCKERFILE - Healthcare Application Container
#
# PURPOSE: Creates a Docker image with our application
#
# WHY: Allows running app in containers (Docker/Kubernetes)
#
# USAGE: docker build -t healthcare-app:1.0.0 .

# BASE IMAGE
# WHY: Start with Tomcat that has Java 21
FROM tomcat:9.0-jdk21-openjdk-slim

# MAINTAINER INFO
LABEL maintainer="healthcare@example.com"
LABEL description="Healthcare Management System"
LABEL version="1.0.0"

# REMOVE DEFAULT WEBAPPS
# WHY: Clean slate, remove example apps for security
RUN rm -rf /usr/local/tomcat/webapps/*

# COPY APPLICATION
# WHY: Add our WAR file to Tomcat's webapps directory
# WHAT: Tomcat will auto-deploy the WAR on startup
COPY target/healthcare.war /usr/local/tomcat/webapps/ROOT.war

# EXPOSE PORT
# WHY: Documents which port the container listens on
EXPOSE 8080

# HEALTH CHECK
# WHY: Docker can verify if container is healthy
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/ || exit 1

# START COMMAND
# WHY: Tells Docker what to run when container starts
CMD ["catalina.sh", "run"]
