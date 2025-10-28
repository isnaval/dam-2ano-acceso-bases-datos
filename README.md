# ?? DAM 2º Año - Acceso a Bases de Datos

Proyecto de ejercicios del módulo de Acceso a Datos organizados por bloques temáticos.

## ??? Estructura del Proyecto

### Módulos Principales

- **`common-utils/`** - Utilidades compartidas (HibernateUtil, DatabaseUtil, etc.)
- **`R1-serializacion-ficheros/`** - Serialización, JSON, XML y gestión de ficheros
- **`R2-jdbc/`** - Acceso a bases de datos con JDBC (MySQL y SQLite)
- **`R3-hibernate-jpa/`** - ORM con Hibernate y JPA

## ?? Compilar y Ejecutar

### Compilar todo el proyecto
```bash
mvn clean install
```

### Compilar un módulo específico
```bash
cd R1-serializacion-ficheros
mvn clean compile
```

### Ejecutar ejercicios
```bash
cd R1-serializacion-ficheros
mvn exec:java -Dexec.mainClass="es.dam.r1.jackson.Main"
```

## ??? Tecnologías

- Java 17
- Maven (multi-módulo)
- Hibernate 6.x / JPA
- MySQL / SQLite
- Jackson / Gson (JSON)
- JUnit 5 (testing)

## ?? Documentación

Ver la carpeta `docs/` para más información.

## ????? Autor

Proyecto educativo - DAM 2º Año
