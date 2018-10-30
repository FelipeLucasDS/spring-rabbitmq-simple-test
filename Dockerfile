FROM openjdk:8-jre-alpine

ENV HOME=/home/gfn_backend_challenge_felipe-Lucas

WORKDIR $HOME

ADD RabbitMQSpringTest-0.0.1-SNAPSHOT.jar RabbitMQSpringTest-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "RabbitMQSpringTest-0.0.1-SNAPSHOT.jar"]