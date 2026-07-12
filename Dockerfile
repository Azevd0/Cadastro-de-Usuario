FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests



FROM public.ecr.aws/lambda/java:21

COPY --from=build /app/target/userregistryapi-0.0.1-SNAPSHOT.jar ${LAMBDA_TASK_ROOT}/lib/

CMD [ "br.com.davyson.userregistryapi.StreamLambdaHandler::handleRequest" ]