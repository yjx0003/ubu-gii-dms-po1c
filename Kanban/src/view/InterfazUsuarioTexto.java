package view;


import java.util.Map;
import java.util.Scanner;


import controller.Controlador;
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
	
	public int getOpcionUsuario(){
		return this.opcionUsuario; 
	}

	private int opcionMenu(String preguntaOpcion, Map<Integer, ?> mapa, int... numValidos) {
		
	
		while (true) {
			try {
				
				System.out.println(preguntaOpcion);

				int opcion =Integer.valueOf(sc.next());
				
				
				if (ArrayIntContains(numValidos, opcion)
						|| (mapa != null && mapa.containsKey(opcion))) {
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
	private boolean ArrayIntContains(int[] valores,int buscado) {
		for(int v:valores) {
			if (v==buscado) return true;
		}
		return false;
	}

	private int opcionMenu(String preguntaOpcion, int... numValidos) {
		return opcionMenu(preguntaOpcion, null, numValidos);

	}

	public void menuPrincipal() {
		System.out.println("********MENÚ SCRUM********\n");
		System.out.println("[1] Product Backlog");
		System.out.println("[2] Sprint Backlog");
		System.out.println("[3] Gestión de Miembros ");
		System.out.println("[4] Guardar y cerrar");
		this.opcionUsuario = opcionMenu("Eliga una opción: ", 1, 2, 3,4);

	}

	public void menuProductBacklog(ProductBacklog productBacklog) {
		System.out.println("*******PRODUCT BACKLOG********\n");
		System.out.println("Tareas: ");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		System.out.println("[0] Atrás");
		System.out.println("[1] Añadir tarea");
		this.opcionUsuario = opcionMenu("Elige una opción", productBacklog.getTareas(), 0, 1);
	}

	public void menuTarea(Tarea t) {
		System.out.println("*******TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripción: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		System.out.println("[1] Modificar tarea");
		System.out.println("[0] Atrás");
		this.opcionUsuario = opcionMenu("Elige una opción", 0, 1);
	}

	public void menuModificarTarea(Tarea t, Map<Integer, MiembroDeEquipo> miembros, Backlog backlog) {
		System.out.println("*******MODIFICAR TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripción: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		
		System.out.println("**Se deben dejar los campos vacíos si no se quieren modificar. **");

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
		System.out.println("Nuevo miembro: ");
		String nuevoMiembro = sc.nextLine();
		if (!nuevoMiembro.isEmpty()) {
			do {
				if (!miembros.containsKey(nuevoMiembro)) {
					System.out.println("Opción incorrecta. Nuevo Miembro: ");
					opcionUsuario = sc.nextInt();
				}
			} while (!miembros.containsKey(nuevoMiembro));
		}
		sc.close();

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
		System.out.println("Nuevo miembro: ");
		String nuevoMiembro = sc.nextLine();
		if (!nuevoMiembro.isEmpty()) {
			do {
				if (!miembros.containsKey(nuevoMiembro)) {
					System.out.println("Opción incorrecta. Nuevo Miembro: ");
					nuevoMiembro = sc.nextLine();
				}
			} while (!miembros.containsKey(nuevoMiembro));
		}
		System.out.println("Tipo de requisito asociado: ");
		System.out.println("[1] Historia de Usuario");
		System.out.println("[2] Defecto");
		
		int intopcion = opcionMenu("Elige el tipo de requisito",1,2);
		String actor = "";
		String tarea = "";
		switch (intopcion) {
		case 1:
			System.out.println("Actor: ");
			actor = sc.nextLine();
			break;
		case 2:
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
		System.out.println("");
		System.out.println("[1] Añadir tarea al Sprint.");
		System.out.println("[2] Mover tarea.");
		System.out.println("[3] Crear nuevo Sprint.");
		System.out.println("[0] Atrás.");


		opcionUsuario=opcionMenu("Escoja una opción o el ID de la tarea que quiere visualizar: ", sprintBacklog.getTareas(),0,1,2,3);
	}

	public void menuAnadirTareaSprint(ProductBacklog productBacklog) {
		System.out.println("*******AÑADIR TAREA A SPRINT********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}
		opcionUsuario=opcionMenu("Escoja la tarea que se desea añadir o 0 para salir: ",productBacklog.getTareas(),0);

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


		opcionUsuario=opcionMenu("Escoja la tarea que se desea mover o 0 para salir: ",sprintBacklog.getTareas(), 0);

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
		System.out.println("Descripcion: ");
		String descripcion = sc.nextLine();

		System.out.print("¿Desea crear el nuevo sprint? (no se tendrá acceso al sprint anterior)");
		System.out.println("[1] Crear nuevo Sprint.");
		System.out.println("[0] Cancelar.");
		
		opcionUsuario=opcionMenu("Elige una opcion: ",0,1);

		if (opcionUsuario == 1) {
			controladorKanban.anadirNuevoSprint(dia, mes, ano, descripcion);
		}

	}

	public void menuMiembros(Map<Integer, MiembroDeEquipo> miembros) {
		System.out.println("*******MIEMBROS DE EQUIPO********\n");
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		System.out.println("[1] Añadir Miembro.");
		System.out.println("[0] Atrás.");


		opcionUsuario=opcionMenu("Escoja una opcion: ", 0,1);
		

	}

	public void menuAnadirMiembro() {
		System.out.println("*******CREAR NUEVO MIEMBRO*******\n");

		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		System.out.println("¿Desea crear un miembro con el nobmre " + nombre + "?");
		System.out.println("[1] Crear miembro.");
		System.out.println("[0] Cancelar.");


		opcionUsuario=opcionMenu("Escoja una opción: ", 0,1);

		if (opcionUsuario == 1) {
			controladorKanban.anadirNuevoMiembro(nombre);
		}

	}

	public void init(Controlador controlador) {
		this.sc=new Scanner(System.in);
		this.controladorKanban = controlador;
	}

}