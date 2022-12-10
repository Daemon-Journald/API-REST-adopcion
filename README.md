# Spring Boot, MySQL, JPA, Rest API

API Restful CRUD para una tienda de mascotas usando Spring Boot, Mysql, JPA e Hibernate.

## Pasos para la Configuración

**1. Clonar el código de la Aplicación**

```bash
git clone https://github.com/gitiing/API-REST-adopcion.git
```

**2. Crear la base de datos en Mysql**
```bash
create database DBadopcion
```
- run `src/main/resources/blogapi.sql`

**3.
Cambie el nombre de usuario y la contraseña de Mysql según su instalación de mysql**

+ abrir `src/main/resources/application.properties`
+ cambiar `spring.datasource.username` y `spring.datasource.password` según su instalación de mysql

**4. Ejecute la aplicación en la terminal usando maven**

```bash
mvn spring-boot:run
```
La aplicación comenzará a ejecutarse en <http://localhost:8080>

## Explorar el API Rest

La aplicación define las siguientes API CRUD.

### Persona

| Metodo | Url                      | Descrición                                | Sample Valid Request Body |
|--------|--------------------------|-------------------------------------------| ------------------------- |
| GET    | /persona                 | Obtiene todas las personas registradas    | |
| GET    | /persona/{id}            | Obtiene una persona con un determinado id | |
| POST   | /persona                 | Registra una nueva persona                | |
| DELETE | /persona/{id}            | Elimina una persona segun su id           | |

### Refugio y Animal

| Method | Url                                    | Description                                                          | Sample Valid Request Body |
|--------|----------------------------------------|----------------------------------------------------------------------|---------------------------|
| GET    | /refugio                               | Obtiene los refugios con el numero de animales que contiene cada uno |                           |
| GET    | /refugio/{idRefugio}/animal            | Obtiene los animales que existen en un refugio determinado           |                           |
| GET    | /refugio/{idRefugio}/animal/{idAnimal} | Obtiene un animal por el ID dependiendo en que refugio se encuentre  |                           |
| POST   | /refugio                               | Registra un nuevo refugio                                            |                           |
| POST   | /refugio/{idRefugio}/animal            | Registra un animal en un refugio determinado                         |                           | 
| DELETE | /refugio/6                             | Elimina el refugio y los animales que existen en dicho refugio       |                           |

### Adopcion

| Method | Url                        | Description                          | Sample Valid Request Body |
| ------ |----------------------------|--------------------------------------|---------------------------|
| GET    | /adopcion                  | Obtiene todas las adopciones creadas |                           |
| GET    | /adopcion/{idAdopcion}     | Obtiene una adopcion por el ID       |                           |
| POST   | /adopcion                  | Crea una nueva adopcion              |                           | 
| DELETE | /adopcion/{idAdopcion}     | Elimina una adopcion por el ID       |                           |

Pruebalos usando postman.

## Ejemplos de solicitudes JSON válidos

##### <a id="crearpersona">Crear Persona -> /persona</a>
```json
{
    "personaId": 1,
    "personaNombre": "Minerva",
    "personaEmail": "mbabalola0@slate.com",
    "personaDireccion": "8 Basil Point",
    "personaTelefono": "481-188-4396"
}
```

##### <a id="crearanimal">Crear Animal -> /refugio/{idRefugio}/animal</a>
```json
{
    "nombre": "Giant anteater",
    "sexo": "F",
    "edad": 8,
    "talla": 92,
    "raza": "Navajo"
}
```

##### <a id="crearrefugio">Crear Refugio -> /refugio</a>
```json
{
      "nombre": "Perritos Felices",
      "ciudad": "San Sebastian",
      "direccion": "21442 Reindahl Circle"
  
}

```

##### <a id="crearadopcion">Crear Adopcion -> /adopcion</a>
```json
{
      "personaId": 1,
      "animalId": 19
}
```