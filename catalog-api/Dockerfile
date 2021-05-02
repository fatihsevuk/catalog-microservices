FROM adoptopenjdk/openjdk11:ubi as catalog-api-build
WORKDIR /app

### Step 1 - Copy pom. xml and download project dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

##### Stage 2 - Let's build a minimal image with the "deployable package"
FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
ARG DEPENDENCY=/app/target/dependency
COPY --from=catalog-api-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=catalog-api-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=catalog-api-build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.catalog.api.CatalogApiApplication"]
