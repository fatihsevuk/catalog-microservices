FROM adoptopenjdk/openjdk11:ubi as catalog-importer-build
WORKDIR /app

### Step 1 - Copy pom. xml and download project dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

##### Step 2 - Let's build a minimal image with the "deployable package"
FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
ARG DEPENDENCY=/app/target/dependency
COPY --from=catalog-importer-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=catalog-importer-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=catalog-importer-build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.catalog.importer.CatalogImporterApplication"]

