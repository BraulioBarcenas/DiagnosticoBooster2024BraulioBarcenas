
# Assigment Inicial Booster 2024

Informe del diagnostico:

El diagnóstico cubria temas que no eran de mi conocimiento o no tenia práctica con ellos, como por ejemplo, Docker, realicé una implementación simple según la investigación que pude realizar, sin embargo, debido a que solo se disponia de 1 semana para la realización del assigment, hubo algunos detalles que faltaron del assigment por falta de tiempo:

- Login en la interfaz
- Un mejor control de excepciones
- Control de bugs

En general me sentí cómodo durante el desarrollo, sin embargo la falta de tiempo fue un factor determinante sobre mi desempeño en el mismo. 

## Descripción del proyecto

El proyecto consiste en el control de asistencias de sesiones  de conferencias, donde se registran usuarios que pueden ser luego inscritos a sesiones igualmente creadas por medio de la interfaz.

Esta montado con una API en SpringBoot conectada a PostgreSQL, y mostrado por medio de un frontend hecho con ReactJS.

En el documento adjunto a la entrega del assigment se incluye el diagrama ER de la base de datos.

## Documentación

La documentación sobre los endpoint se encuentra en este pastebin: https://pastebin.com/DwwbP3mJ

La utilización de la interfaz se encuentra en el PDF adjunto a la entrega.

## Iniciar el proyecto

El proyecto creará la base de datos por su cuenta, además de descargar las dependencias necesarias para su funcionamiento. Los comandos para inicializar el proyecto son los siguiente:

1. `./mvnw clean package -DskipTests` 
2. `docker-compose up --build`

Finalmente, el proyecto estará disponible en el puerto 3000 del localhost.
