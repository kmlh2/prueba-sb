
# Build the app with cache dependencies
FROM amazoncorretto:11.0.17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM amazoncorretto:11.0.17-alpine AS runner
WORKDIR /usr/src/app

COPY --from=builder /app/build/libs ./dist

EXPOSE 8080
CMD ["java","-jar","dist/prueba-0.0.1-SNAPSHOT.jar"]


#CMD [ "node","dist/main" ]