FROM tomcat:latest
COPY target/jackspetitions.war /usr/local/tomcat/webapps/
EXPOSE 8081
CMD ["catalina.sh", "run", "-catalina.http.port=8081"]