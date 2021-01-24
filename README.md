# Meetup modular domain using Domain Driven Design

## Tabla de contenidos

[1. Dominio](#1-Dominio)
&nbsp;&nbsp;[1.1 Descripción](#11-descripcion)

&nbsp;&nbsp;[1.2 Modelo Conceptual](#12-modelo-conceptual)

&nbsp;&nbsp;[1.3 Event Storming](#13-event-storming)

## 1. Dominio

### 1.1 Descripción

**Definición:**

Como objetivo de este proyecto se ha seleccionado el dominio de **Grupos de reunión** basado en el sistema [Meetup.com](https://www.meetup.com/).

**Las principales razones para seleccionar este dominio son:**

- Es bastante frecuente que mucha gente use el site Meetup para organizar o asistir a reuniones
- Ya existe un sistema que lo implementa, por lo que cualquiera puede comprobar la implementación contra un site en productivo
- Es un dominio poco complejo y fácil de entender
- No es trivial, es decir, hay reglas de negocio y lógica, por lo que no son operaciones CRUD
- No se necesita tener un conocimiento exhaustivo del dominio a diferencia de otros dominios como banca, salud, etc...
- No es un dominio grande, así que es fácil de implementar

Entre los requisitos que nos vamos a encontrar:

**Usuarios**

 - Cada `Administrador`, `Miembro` y `Pagador` es un `Usuario`. 

 - Para ser un `Usuario`, se requiere que el usuario se registre previamente (`Registro de Usuario`) que confirme el registro. 

 - El usuario sólo puede registrarse una sola vez.

 - Un registro de usuario puede expirar mientras esté pendiente de confirmación.
   
 - El usuario deberá confirmar el registro de usuario para poder ser registrado en el sistema.
   
 - A cada `Usuario` se le asigna uno o más `Roles de Usuario`.

 - Cada `Rol de Usuario` tiene un conjunto de `Permisos`. Un `Permiso` define si un `Usuario` puede realizar una acción particular.

**Administración**

Para crear una nueva `Reunión de Grupo`, un `Miembro` necesita proponer el grupo. 

Una `Propuesta de Reunión de Grupo` se envía a los `Administradores`. 

Un `Administrador` puede aceptar o rechazar una `Propuesta de Reunión de Grupo`. 

Si una `Propuesta de Reunión de Grupo` se acepta entonces se crea la `Reunión de Grupo`.
