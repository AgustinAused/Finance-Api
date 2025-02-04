
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
- Maven
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

## Despliegue con Docker

Para facilitar el despliegue y la configuración de los servicios, se utiliza Docker Compose. Asegúrate de tener Docker y Docker Compose instalados.

1. Construir y levantar los contenedores:

```bash
docker-compose up --build
```

2. La aplicación estará disponible en `http://localhost:8080`.

## Uso de la API

### Endpoints Principales

- `POST /api/expenses`: Registrar un nuevo gasto
- `POST /api/incomes`: Registrar un nuevo ingreso
- `GET /api/analytics`: Obtener análisis financiero en tiempo real

### Ejemplo de Petición

Registrar un nuevo gasto:

```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
        "amount": 100.0,
        "description": "Compra de material de oficina",
        "date": "2024-07-01"
      }'
```
## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del proyecto.
2. Crea una rama nueva (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva característica'`).
4. Envía tus cambios a tu fork (`git push origin feature/nueva-caracteristica`).
5. Crea un Pull Request en GitHub.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más información.

## Contacto

Para cualquier consulta o sugerencia, puedes contactarme en [agus.aused@gmail.com](mailto:agus.aused@gmail.com).
