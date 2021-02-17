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

 - Para crear un nuevo `Grupo de Meetup`, un `Miembro` debe hacer una `Propuesta de Grupo de Meetup`.

 - Para proponer una `Propuesta de Grupo de Meetup` se necesita indicar un nombre, una descripción, la localización, el usuario que la propone y la fecha del meetup.

 - Una `Propuesta de Grupo de Meetup` se envía a los `Administradores` y éste puede aceptar o rechazar la propuesta en un momento determinado. Si la rechaza debe indicar el motivo del rechazo.

 - Si una `Propuesta de Grupo de Meetup` se acepta entonces se crea el `Grupo de Meetup`.

**Meetings**

***Propuesta de grupo de Meetup***

 - Para crear un nuevo `Grupo de Meetup`, un `Miembro` hacer una `Propuesta de Grupo de Meetup`. 
   
 - Para proponer una `Propuesta de Grupo de Meetup`, el `Miembro` necesita indicar un nombre, una descripción y la localización.

 - La propuesta de grupo de meetup se puede aceptar.

 - No se puede aceptar una propuesta de grupo de meetup cuando ésta ya ha sido aceptada.
  
 - Una vez aceptada la propuesta de grupo de meetup, se puede crear el grupo de meetup y el creador 
   de la propuesta se convierte en miembro organizador del grupo de meetup.

***Grupos de Meetup***

 - Un grupo de meetup se crea una vez que una propuesta de creación del grupo esté aceptada, en ese momento se
   podrá crear.

 - La información de un grupo de meetup son el nombre, la descripción, la localización, el creador y la fecha de
   creación del grupo de meetup.

 - El creador del grupo podrá actualizar el nombre, la descripción y la localización del grupo de meetup.

 - Al grupo de meetup se podrán añadir nuevos miembros, pero no miembros ya existentes.

 - Un miembro del grupo de meetup puede dejar el grupo en cualquier momento, pero el creador del grupo de meetup 
   no puede dejar el grupo.

***Comentarios en el meeting***

 - Un `Miembro` de un `Grupo de Meetup` puede añadir comentarios a un `Meeting` existente. El comentario debe ser obligatorio.

 - Un usuario puede añadir comentarios si es `Miembro` del `Grupo de Meetup`. 

 - Los comentarios sólo podrán añadirse si éstos están habilitados por configuración.
