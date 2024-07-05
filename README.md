
## Finance-Api: Proyecto de Gestión de Finanzas Empresariales

Finance-App es una aplicación web backend desarrollada en Java utilizando el framework Spring Boot. Esta aplicación permite la gestión de finanzas de una empresa ficticia, incluyendo el registro de ingresos y gastos, y ofrece análisis en tiempo real.

## Estado del Proyecto

⚠️ **En Desarrollo**: Este proyecto está actualmente en desarrollo y no está listo para su uso en producción.

## Características

- Subida y gestión de gastos.
- Subida y gestión de ingresos.
- Análisis de datos financieros en tiempo real.
- API RESTful
- Autenticación y autorización básica

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Docker
- Gradle
- Spring Security

## Instalación

### Requisitos Previos

- [Java JDK 21](https://adoptium.net/)
- [Git](https://git-scm.com/)
- [Gradle](https://gradle.org/) o [Maven](https://maven.apache.org/)

### Clonar el Repositorio

1. Clonar el repositorio:

``` bash
git clone https://github.com/AgustinAused/Finance-Api.git
```
``` bash
cd Finance-Api
```
2. Crear un archivo .env en el directorio raíz del proyecto y definir las siguientes variables de entorno:
```
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_contraseña
SPRING_SECURITY_USER_NAME=admin
SPRING_SECURITY_USER_PASSWORD=admin_password
```
3. Construir el proyecto con Gradle
``` bash
./gradlew build
```

## Despliegue con Docker

Para facilitar el despliegue y la configuración de los servicios, se utiliza Docker Compose. Asegúrate de tener Docker y Docker Compose instalados.

1. Construir y levantar los contenedores:

```bash
docker-compose up --build
```

2. La aplicación estará disponible en `http://localhost:8080`.


Este README cubre los aspectos más importantes del proyecto, como la descripción, tecnologías, configuración, despliegue, uso de la API, estructura del proyecto y cómo contribuir. Puedes adaptarlo según las necesidades específicas de tu proyecto.

