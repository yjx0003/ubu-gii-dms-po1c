package view;

import java.util.Map;
import java.util.Scanner;

import controller.Controlador;
import controller.OpcionesMenu;
import model.Backlog;
import model.EstadoTarea;
import model.EstadoTareaCompletada;
import model.EstadoTareaEnProceso;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.SprintBacklog;
import model.Tarea;

/**
 * Clase de la interfaz de usuario en texto, imprime todos los menus.
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class InterfazUsuarioTexto {

	private Controlador controladorKanban;

	private int opcionUsuario;

	private static InterfazUsuarioTexto instancia = null;

	private Scanner sc;

	private InterfazUsuarioTexto() {

	}

	public static InterfazUsuarioTexto getInstancia() {
		if (instancia == null) {
			instancia = new InterfazUsuarioTexto();
		}
		return instancia;
	}
	/**
	 * Devuelve la opcion de usuario en el menu actual.
	 * @return opcion de usuario.
	 */
	public int getOpcionUsuario() {
		return this.opcionUsuario;
	}
	/**
	 * Bucle infinito hasta que se devuelva una opcion valida por el usuario
	 * @param preguntaOpcion texto que se muestra
	 * @param mapa mapa de miembros, product back log y sprint backlog o null 
	 * @param opcionesValidos OpcionesMenu validos
	 * @return
	 */
	private int opcionMenu(String preguntaOpcion, Map<Integer, ?> mapa, OpcionesMenu... opcionesValidos) {
		System.out.println("");
		for (int i = 0; i < opcionesValidos.length; i++) {
			System.out.println("[" + i + "] " + opcionesValidos[i].getNombre());
		}

		while (true) {
			try {

				System.out.println(preguntaOpcion);

				int opcion = Integer.valueOf(sc.nextLine());

				if (opcion >= 0 && opcion < opcionesValidos.length) {
					return opcionesValidos[opcion].ordinal();
				} else if (mapa != null && mapa.containsKey(opcion)) {
					return opcion;
				}
				System.out.println(opcion + " no es un número válido");

			} catch (NumberFormatException e) {
				System.out.println("Tiene que ser un número");

			}

		}
	}
	/**
	 * Cierra el scanner de teclado
	 */
	public void cerrarRecursos() {
		sc.close();
	}
	/**
	 * Bucle infinito hasta que se devuelva una opcion valida por el usuario
	 * @param preguntaOpcion texto que se muestra
	 * @param opcionesValidos OpcionesMenu validos
	 * @return
	 */
	private int opcionMenu(String preguntaOpcion, OpcionesMenu... opcionesValidos) {
		return opcionMenu(preguntaOpcion, null, opcionesValidos);

	}
	/**
	 * Menu inicial.
	 */
	public void menuPrincipal() {
		System.out.println("********MENÚ SCRUM********\n");

		this.opcionUsuario = opcionMenu("Eliga una opción: ", OpcionesMenu.PRODUCT_BACKLOG, OpcionesMenu.SPRINT_BACKLOG,
				OpcionesMenu.GESTION_MIEBROS, OpcionesMenu.GUARDAR_Y_CERRAR);

	}
	/**
	 * Menu del product backlog, muestra todas las tareas de ese backlog
	 * @param productBacklog productBacklog
	 */
	public void menuProductBacklog(ProductBacklog productBacklog) {
		System.out.println("*******PRODUCT BACKLOG********\n");
		System.out.println("Tareas: ");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		this.opcionUsuario = opcionMenu("Elige una opción", productBacklog.getTareas(), OpcionesMenu.ATRAS,
				OpcionesMenu.ANADIR_TAREA);
	}
	/**
	 *  Muestra los datos de tarea
	 * @param t tarea a mostrar
	 */
	public void menuTarea(Tarea t) {
		System.out.println("*******TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripción: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		this.opcionUsuario = opcionMenu("Elige una opción", OpcionesMenu.MODIFICAR_TAREA, OpcionesMenu.ATRAS);
	}
	/**
	 * Menu de modificar la tarea.
	 * @param t tarea a modificar
	 * @param miembros todos los miembros a elegir uno
	 * @param backlog backlog de la tarea
	 */
	public void menuModificarTarea(Tarea t, Map<Integer, MiembroDeEquipo> miembros, Backlog backlog) {
		System.out.println("*******MODIFICAR TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripción: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		System.out.println("\n**Se deben dejar los campos vacíos si no se quieren modificar. **\n");

		System.out.println("Nuevo título: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripción: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		int nuevoMiembro = opcionMenu("Nuevo miembro", miembros, OpcionesMenu.MANTENER_MIEMBRO);
		if (nuevoMiembro == OpcionesMenu.MANTENER_MIEMBRO.ordinal()) {
			nuevoMiembro = 0;
		}
		controladorKanban.modificarTarea(t.getIdTarea(), nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, backlog);

	}
	/**
	 * Menu de añadir tarea.
	 * @param miembros miembros donde se elegira uno
	 */
	public void menuAnadirTarea(Map<Integer, MiembroDeEquipo> miembros) {

		System.out.println("Nuevo título: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripción: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		int nuevoMiembro = opcionMenu("Nuevo miembro", miembros);
		System.out.println("Tipo de requisito asociado: ");

		int intopcion = opcionMenu("Elige el tipo de requisito", OpcionesMenu.HISTORIA_DE_USUARIO,
				OpcionesMenu.DEFECTO);
		String actor = "";
		String tarea = "";

		switch (OpcionesMenu.values()[intopcion]) {
		case HISTORIA_DE_USUARIO:
			System.out.println("Actor: ");
			actor = sc.nextLine();
			break;
		case DEFECTO:
			System.out.println("Defecto asociado a la tarea: ");
			tarea = sc.nextLine();
			break;
		default:
			System.err.println("Error");

		}

		boolean anadida = controladorKanban.anadirTarea(nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, actor, tarea);
		if (!anadida) {
			System.out.println("La tarea no se ha podido añadir. Alguno de los campos era incorrecto. ");
		}

	}
	/**
	 * Menu del sprint backlog
	 * @param sprintBacklog sprintbacklog
	 */
	public void menuSprintBacklog(SprintBacklog sprintBacklog) {
		System.out.println("*******SPRINT BACKLOG********\n");
		System.out.println(sprintBacklog.getDescripcion());
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println(
					"[" + par.getKey() + "] " + par.getValue().getTitulo() + " Estado: " + par.getValue().getEstado());
		}

		opcionUsuario = opcionMenu("Escoja una opción o el ID de la tarea que quiere visualizar: ",
				sprintBacklog.getTareas(), OpcionesMenu.ANADIR_TAREA_AL_SPRINT, OpcionesMenu.MOVER_TAREA,
				OpcionesMenu.CREAR_NUEVO_SPRINT, OpcionesMenu.ATRAS);
	}
	/**
	 * Menu de añadir tareas al sprintbacklog
	 * @param productBacklog productBacklog
	 */
	public void menuAnadirTareaSprint(ProductBacklog productBacklog) {
		System.out.println("*******AÑADIR TAREA A SPRINT********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		opcionUsuario = opcionMenu("Escoja la tarea que se desea añadir o 0 para salir: ", productBacklog.getTareas(),
				OpcionesMenu.ATRAS);

		if (opcionUsuario != OpcionesMenu.ATRAS.ordinal()) {
			controladorKanban.anadirTareaSprint(opcionUsuario);
		}
	}
	/**
	 * Menu de mover tarea del product backlog al sprint backlog
	 * @param productBacklog productBacklog
	 * @param sprintBacklog sprintBacklog
	 */
	public void menuMoverTarea(ProductBacklog productBacklog, SprintBacklog sprintBacklog) {
		System.out.println("*******MOVER TAREA********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println(
					"[" + par.getKey() + "] " + par.getValue().getTitulo() + "Estado: " + par.getValue().getEstado());
		}

		opcionUsuario = opcionMenu("Escoja la tarea que se desea mover o 0 para salir: ", productBacklog.getTareas(),
				OpcionesMenu.ATRAS);

		if (opcionUsuario != OpcionesMenu.ATRAS.ordinal()) {

			controladorKanban.moverTarea(opcionUsuario);

		}

	}
	/**
	 * Menu crear nuevo sprint
	 */
	public void menuCrearNuevoSprint() {
		System.out.println("*******CREAR NUEVO SPRINT********\n");

		System.out.println("Día del mes: ");
		int dia = sc.nextInt();
		System.out.println("Mes: ");
		int mes = sc.nextInt();
		System.out.println("Año: ");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Descripcion: ");
		String descripcion = sc.nextLine();

		System.out.println("¿Desea crear el nuevo sprint? (no se tendrá acceso al sprint anterior)");

		opcionUsuario = opcionMenu("Elige una opcion: ", OpcionesMenu.ACEPTAR, OpcionesMenu.CANCELAR);

		if (opcionUsuario == OpcionesMenu.ACEPTAR.ordinal()) {
			controladorKanban.anadirNuevoSprint(dia, mes, ano, descripcion);
		}

	}
	/**
	 * Menu de mostrar los miembros de equipo
	 * @param miembros miembros
	 */
	public void menuMiembros(Map<Integer, MiembroDeEquipo> miembros) {
		System.out.println("*******MIEMBROS DE EQUIPO********\n");
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("-" + par.getValue().getNombre());
		}
		opcionUsuario = opcionMenu("Escoja una opcion: ", OpcionesMenu.ANADIR_MIEMBRO, OpcionesMenu.ATRAS);

	}
	/**
	 * Menu de añadir un miembro.
	 */
	public void menuAnadirMiembro() {
		System.out.println("*******CREAR NUEVO MIEMBRO*******\n");

		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		System.out.println("¿Desea crear un miembro con el nobmre " + nombre + "?");

		opcionUsuario = opcionMenu("Escoja una opción: ", OpcionesMenu.ACEPTAR, OpcionesMenu.CANCELAR);

		if (opcionUsuario == OpcionesMenu.ACEPTAR.ordinal()) {
			controladorKanban.anadirNuevoMiembro(nombre);
		}
	}
	/**
	 * Inicia la vista instanciando el scanner y asignando el controlador
	 * @param controlador controlador
	 */
	public void init(Controlador controlador) {
		this.sc = new Scanner(System.in);
		this.controladorKanban = controlador;
	}
	/**
	 * Menu de pedir siguiente estado
	 * @return nuevo estado
	 */
	public EstadoTarea pedirSiguienteEstado() {
		System.out.println("¿Es necesario que la tarea vuelva al estado \"En Proceso\"?");
		int respuesta = opcionMenu("Escoja una opción: ", OpcionesMenu.SI, OpcionesMenu.NO);
		if (respuesta == OpcionesMenu.SI.ordinal()) {
			return EstadoTareaEnProceso.getInstancia();
		} else {
			return EstadoTareaCompletada.getInstancia();
		}

	}

}