# Notas iniciales

Notas iniciales sobre el diseño de la aplicación, centradas en el modelo de datos.

## Modelo de datos

### Entidades

Las entidades del modelo identificadas son:

- `Tarea`: Almacenar los detalles de cada tarea.
  - **Atributos**: `titulo`, `descripcion`, `coste`, `beneficio`, `-requisito:Requisito[1]` (composición), `-asignadoA:Miembro[0..1]`
  - **Métodos**: `Tarea(Requisito)`, `asignarMiembro(MiembroDeEquipo)`
- `MiembroDeEquipo`: Almacenar los datos de cada miembro del equipo.
  - **Atributos**:
  - **Métodos**:
- `Backlog`: Agrupar tareas.
  - **Atributos**: `#tareas:Tarea[*]` 
  - **Métodos**: 
- `ProductBacklog`: Agrupar las tareas pendientes y moverlas a un sprint.
  - **Hereda de**: `Backlog`
  - **Atributos**: 
  - **Métodos**: 
- `SprintBacklog`: Agrupar las tareas de cada sprint y mover las tareas entre los diferentes estados.
  - **Hereda de**: `Backlog`
  - **Atributos**: 
  - **Métodos**: 
- `Requisito`: Detalles comunes de un requisito.
  - **Atributos**: 
  - **Métodos**: 
- `HistoriaDeUsuario`: Detalles específicos de una historia de usuario.
  - **Hereda de**: `Requisito`
  - **Atributos**: 
  - **Métodos**: 
- `Defecto`: Detalles específicos de un defecto.
  - **Hereda de**: `Requisito`
  - **Atributos**: 
  - **Métodos**: 

#### Razonamientos del diseño de las entidades

- Se define una relación en la que cada backlog contiene un número arbitrario de tareas. Así sólo necesita tener cargadas las tareas del backlog y su localización es inmediata. Pero hay que controlar externamente que una tarea no se asigne a varios backlogs.
- Su contrapartida (**descartada**), que una tarea tenga una relación al backlog que la contiene, implica que tienen que cargarse todas para poder filtrar las asociadas al backlog que se está inspeccionando, pero garantiza que una tarea **sólo** está asignada a un único backlog.