FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM public.ecr.aws/lambda/java:21

COPY --from=build /app/target/dependency/BOOT-INF/classes/ ${LAMBDA_TASK_ROOT}/

COPY --from=build /app/target/dependency/BOOT-INF/lib/ ${LAMBDA_TASK_ROOT}/lib/

COPY --from=build /app/target/dependency/META-INF/ ${LAMBDA_TASK_ROOT}/META-INF/

CMD [ "br.com.davyson.userregistryapi.config.aws.StreamLambdaHandler::handleRequest" ]