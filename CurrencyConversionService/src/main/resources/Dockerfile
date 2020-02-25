FROM openjdk

WORKDIR /usr/app

COPY ./target/currency-conversion-service.jar /usr/app/

EXPOSE 9010

ENTRYPOINT [ "java","-jar", "./currency-conversion-service.jar" ]
