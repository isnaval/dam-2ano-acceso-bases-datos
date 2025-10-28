# ?? DAM 2� A�o - Acceso a Bases de Datos

Proyecto de ejercicios del m�dulo de Acceso a Datos organizados por bloques tem�ticos.

## ??? Estructura del Proyecto

### M�dulos Principales

- **`common-utils/`** - Utilidades compartidas (HibernateUtil, DatabaseUtil, etc.)
- **`R1-serializacion-ficheros/`** - Serializaci�n, JSON, XML y gesti�n de ficheros
- **`R2-jdbc/`** - Acceso a bases de datos con JDBC (MySQL y SQLite)
- **`R3-hibernate-jpa/`** - ORM con Hibernate y JPA

## ?? Compilar y Ejecutar

### Compilar todo el proyecto
```bash
mvn clean install
```

### Compilar un m�dulo espec�fico
```bash
cd R1-serializacion-ficheros
mvn clean compile
```

### Ejecutar ejercicios
```bash
cd R1-serializacion-ficheros
mvn exec:java -Dexec.mainClass="es.dam.r1.jackson.Main"
```

## ??? Tecnolog�as

- Java 17
- Maven (multi-m�dulo)
- Hibernate 6.x / JPA
- MySQL / SQLite
- Jackson / Gson (JSON)
- JUnit 5 (testing)

## ?? Documentaci�n

Ver la carpeta `docs/` para m�s informaci�n.

## ????? Autor

Proyecto educativo - DAM 2� A�o
