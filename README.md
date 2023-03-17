# ecommerce
Desarrollo de un servicio REST usando java 8 y Spring

Las funciones del programa son: 

● Create a cart (which will hold products). This cart MUST be able to
hold products.

● Cart identifier MUST be generated by your application

● A cart MUST be deleted automatically due to 10 minutes of
inactivity

● Get a cart information given its id.

● Add one or more products to that cart with the following
properties:

○ id (numerical)
○ description (alphanumerical)
○ amount (numerical)

● Delete a cart.

Ha sido desplegado con Docker usando 2 contenedores, el que aloja la App y el que aloja una base de datos PostgreSql local.

Desplegamos el Docker:

*docker-compose up -d*

Para importar el proyecto en eclipse:

--Import -> Maven Project

La app se despliega sobre un Tomcat que toma como puero el 9999, por lo que la url para las peticiones HTTP partiría de:

*localhost:9999/*
