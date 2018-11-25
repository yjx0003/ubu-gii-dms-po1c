package view;

import java.util.Map;
import java.util.Scanner;

import controller.Controlador;
import controller.OpcionesMenu;
import model.Backlog;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.SprintBacklog;
import model.Tarea;

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

	public int getOpcionUsuario() {
		return this.opcionUsuario;
	}

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

	public void cerrarRecursos() {
		sc.close();
	}

	private int opcionMenu(String preguntaOpcion, OpcionesMenu... opcionesValidos) {
		return opcionMenu(preguntaOpcion, null, opcionesValidos);

	}

	public void menuPrincipal() {
		System.out.println("********MENÚ SCRUM********\n");

		this.opcionUsuario = opcionMenu("Eliga una opción: ", OpcionesMenu.PRODUCT_BACKLOG, OpcionesMenu.SPRINT_BACKLOG,
				OpcionesMenu.GESTION_MIEBROS, OpcionesMenu.GUARDAR_Y_CERRAR);

	}

	public void menuProductBacklog(ProductBacklog productBacklog) {
		System.out.println("*******PRODUCT BACKLOG********\n");
		System.out.println("Tareas: ");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		this.opcionUsuario = opcionMenu("Elige una opción", productBacklog.getTareas(), OpcionesMenu.ATRAS,
				OpcionesMenu.ANADIR_TAREA);
	}

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

		controladorKanban.modificarTarea(t.getIdTarea(), nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, backlog);

	}

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
		switch (intopcion) {
		case 0:
			System.out.println("Actor: ");
			actor = sc.nextLine();
			break;
		case 1:
			System.out.println("Defecto asociado a la tarea: ");
			tarea = sc.nextLine();
			break;
		}

		boolean anadida = controladorKanban.anadirTarea(nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, actor, tarea);
		if (!anadida) {
			System.out.println("La tarea no se ha podido añadir. Alguno de los campos era incorrecto. ");
		}

	}

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

	public void menuAnadirTareaSprint(ProductBacklog productBacklog) {
		System.out.println("*******AÑADIR TAREA A SPRINT********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}



		opcionUsuario = opcionMenu("Escoja la tarea que se desea añadir o 0 para salir: ", productBacklog.getTareas(),
				OpcionesMenu.ATRAS);

		if (opcionUsuario != 0) {
			controladorKanban.anadirTareaSprint(opcionUsuario);
		}
	}

	public void menuMoverTarea(SprintBacklog sprintBacklog) {
		System.out.println("*******MOVER TAREA********\n");
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println(
					"[" + par.getKey() + "] " + par.getValue().getTitulo() + "Estado: " + par.getValue().getEstado());
		}

		opcionUsuario = opcionMenu("Escoja la tarea que se desea mover o 0 para salir: ", sprintBacklog.getTareas(), OpcionesMenu.ATRAS);

		if (opcionUsuario != 0) {

			controladorKanban.moverTarea(opcionUsuario);

		}

	}

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

		if (opcionUsuario == 0) {
			controladorKanban.anadirNuevoSprint(dia, mes, ano, descripcion);
		}

	}

	public void menuMiembros(Map<Integer, MiembroDeEquipo> miembros) {
		System.out.println("*******MIEMBROS DE EQUIPO********\n");
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("-" + par.getValue().getNombre());
		}
		opcionUsuario = opcionMenu("Escoja una opcion: ", OpcionesMenu.ANADIR_MIEMBRO, OpcionesMenu.ATRAS);

	}

	public void menuAnadirMiembro() {
		System.out.println("*******CREAR NUEVO MIEMBRO*******\n");

		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		System.out.println("¿Desea crear un miembro con el nobmre " + nombre + "?");

		opcionUsuario = opcionMenu("Escoja una opción: ", OpcionesMenu.ACEPTAR, OpcionesMenu.CANCELAR);

		if (opcionUsuario == 0) {
			controladorKanban.anadirNuevoMiembro(nombre);
		}

	}

	public void init(Controlador controlador) {
		this.sc = new Scanner(System.in);
		this.controladorKanban = controlador;
	}

}