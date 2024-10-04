**Aplicación de Alquiler de Coches**

Esta es una aplicación web desarrollada con **Spring Boot** y **Thymeleaf** que permite la gestión de alquileres de coches. En ella se pueden gestionar los modelos de coches, clientes y el registro de alquileres, indicando fechas de inicio y final para saber cuándo un coche es alquilado por un cliente determinado.

La aplicación está configurada para ejecutarse en el puerto **8000** y utiliza una base de datos en memoria con **H2**.

**Características**

**Gestión de Modelos de Coches**

- Permite registrar diferentes modelos de coches que luego estarán disponibles para ser alquilados.
- Los modelos pueden tener atributos como matrícula, marca, modelo y otros detalles importantes.

**Gestión de Clientes**

- Se puede gestionar un listado de clientes que alquilan coches.
- Los clientes están identificados por su **DNI** y otros datos relevantes.

**Registro de Alquileres**

- En el módulo de alquileres, se registran las transacciones de alquiler de coches.
- Cada alquiler está asociado a un cliente (mediante su **DNI**) y se indican las fechas de **inicio** y **finalización** del alquiler.
- Los coches estarán disponibles solo si no están siendo alquilados durante ese periodo.

**Interfaz Web**

- El front-end está desarrollado con **Thymeleaf** y estilos básicos con **Bootstrap**, ofreciendo una experiencia de usuario sencilla y eficiente.

**Seguridad**

- La aplicación cuenta con un sistema básico de roles basado en **Spring Security**, que restringe el acceso a ciertas operaciones como la eliminación de registros (solo disponible para administradores).

**Configuración del Proyecto**

**Requisitos Previos**

- **Java 17** o superior.
- **Maven** para la gestión de dependencias.
- No es necesario configurar una base de datos externa, ya que la aplicación utiliza una base de datos en memoria **H2**.

**Instalación**

1. Clona el repositorio:

--git clone https://github.com/Hectorjnavas98/producto2\_alquilerdecoches.git

1. Navega al directorio del proyecto:

--cd producto2\_alquilerdecoches

1. Ejecuta la aplicación con Maven:

--mvn spring-boot:run

1. Abre tu navegador y visita la URL:

--http://localhost:8000

**Configuración de Base de Datos**

La aplicación utiliza una base de datos en memoria **H2**. Durante la ejecución, puedes acceder a la consola de H2 para inspeccionar la base de datos mediante la siguiente URL:

--http://localhost:8000/h2-console

Usa las siguientes credenciales para acceder a la consola H2:

- **JDBC URL:** jdbc:h2:mem:testdb
- **Username:** sa
- **Password:** "password"

**Configuración del Puerto**

La aplicación está configurada para ejecutarse en el puerto **8000**. Si deseas cambiar el puerto, puedes modificar el archivo application.properties en src/main/resources:

--server.port=8000

**Dependencias Principales**

- **Spring Boot**: Framework principal para el desarrollo de la aplicación.
- **Thymeleaf**: Motor de plantillas utilizado para la generación de vistas HTML.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **H2**: Base de datos en memoria para pruebas y desarrollo rápido.
- **Spring Security**: Módulo para la gestión de seguridad y roles.
- **Bootstrap**: Para el diseño responsivo de la interfaz de usuario.

**Estructura del Proyecto**

- **Controladores**: Los controladores de Spring están ubicados en el paquete controllers, donde se gestionan las rutas para la interacción de usuarios con la aplicación.
- **Servicios**: El paquete services contiene la lógica de negocio para la gestión de coches, clientes y alquileres.
- **Modelos**: Los modelos de la aplicación, es decir, las entidades que representan coches, clientes y alquileres, están definidos en el paquete models.entities.
- **Vistas**: Las vistas (HTML) están ubicadas en src/main/resources/templates y están diseñadas con Thymeleaf.

**Funcionalidades Futuras**

- Integración con una base de datos persistente como **MySQL** o **PostgreSQL**.
- Implementación de un sistema de autenticación y autorización más robusto.
- Mejora del sistema de alquiler para validar la disponibilidad de coches en función de las fechas.

