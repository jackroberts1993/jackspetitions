FROM tomcat:latest
ADD target/*.war /home/student/Downloads
EXPOSE 8081
CMD ["catalina.sh", "run"]